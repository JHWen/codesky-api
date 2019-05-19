package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.constant.ItemType;
import top.codesky.forcoder.common.constant.ResultEnum;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.params.QuestionAddParam;
import top.codesky.forcoder.model.support.BaseResponse;
import top.codesky.forcoder.model.support.UserInfo;
import top.codesky.forcoder.model.vo.*;
import top.codesky.forcoder.service.AnswerService;
import top.codesky.forcoder.service.FollowService;
import top.codesky.forcoder.service.QuestionService;
import top.codesky.forcoder.service.VoteService;
import top.codesky.forcoder.util.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @Date: 2019/4/20 12:04
 * @Author: codesky
 * @Description: 问题相关的控制层
 */
@Api(tags = "问题功能接口")
@RestController
@RequestMapping(path = "/api")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final VoteService voteService;
    private final FollowService followService;

    @Autowired
    public QuestionController(QuestionService questionService, AnswerService answerService, VoteService voteService, FollowService followService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.voteService = voteService;
        this.followService = followService;
    }

    /**
     * 添加问题
     *
     * @param questionAddParam 添加问题请求封装的参数：title+content
     * @param userInfo         UserInfo in Session
     * @return
     */
    @ApiOperation(value = "添加问题，即提问", notes = "返回操作结果，包含问题的相关信息")
    @PostMapping(path = "/question")
    public BaseResponse<QuestionVO> addQuestion(@RequestBody @Valid QuestionAddParam questionAddParam,
                                                @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {

        Question question = questionService.addQuestion(questionAddParam.getTitle(),
                questionAddParam.getContent(), userInfo.getId());

        QuestionVO questionVO = BeanUtils.copyPropertiesFrom(question, QuestionVO.class);

        return BaseResponse.success(questionVO);
    }

    /**
     * 获取问题的详细信息
     * 包括：问题信息，作者信息，回答信息（暂时默认8条按时间降序排序）
     *
     * @param questionId 问题id
     * @return ResponseVo
     */
    @ApiOperation(value = "获取对应问题的详细信息", notes = "返回问题的详细信息，包含问题的相关回答数据")
    @GetMapping(path = "/question/{questionId}")
    public ResponseEntity<BaseResponse<QuestionDetailsVO>> getQuestionDetails(@PathVariable("questionId") @Positive(message = "问题的id需要为整数") long questionId,
                                                                              @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {

        QuestionDetailsVO questionDetailsVO = questionService.getQuestionDetailsByQuestionId(questionId);

        //判断该问题是否存在
        if (questionDetailsVO != null) {
            //1. 判断用户是否关注问题
            questionDetailsVO.setHasFollow(followService.isFollower(userInfo.getId(),
                    EntityType.QUESTION, questionDetailsVO.getId()));
            questionDetailsVO.getAuthor().setHasFollow(followService.isFollower(userInfo.getId(),
                    EntityType.MEMBER, questionDetailsVO.getAuthor().getId()));
            questionDetailsVO.setFollowerCount((int) followService.getFollowerCount(EntityType.QUESTION,
                    questionDetailsVO.getId()));

            //2. 获取对应的回答列表（暂时默认8条按时间降序排序）
            List<AnswerDetailsVO> answers = answerService.getAnswersByQuestionId(questionDetailsVO.getId());
            //3. 处理当前用户与回答点赞关系以及用户是否关注回答作者
            for (AnswerDetailsVO answer : answers) {
                //统计点赞数
                long voteUpCount = voteService.getVoteUpCount(EntityType.ANSWER, answer.getId());
                answer.setVoteupCount((int) voteUpCount);
                //判断用户之间的关注关系
                answer.getAuthor().setHasFollow(followService.isFollower(userInfo.getId(),
                        EntityType.MEMBER, answer.getAuthor().getId()));
            }
            questionDetailsVO.setAnswers(answers);
            return ResponseEntity.ok(BaseResponse.success(questionDetailsVO));
        }

        return ResponseEntity.badRequest()
                .body(BaseResponse.error(HttpStatus.BAD_REQUEST, ResultEnum.PARAM_IS_INVALID.message()));
    }

    /**
     * 删除问题
     *
     * @param questionId
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "删除问题", notes = "返回操作结果")
    @DeleteMapping(path = "/question/{questionId}")
    public ResponseVo deleteQuestion(@PathVariable("questionId") long questionId,
                                     @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {

        try {
            if (questionService.deleteQuestion(questionId, userInfo.getId())) {
                return ResponseVo.success(ResultEnum.SUCCESS);
            }
        } catch (Exception e) {
            log.error("删除问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 分页获取最新的问题List，按照时间倒序排列
     * 携带参数 offset , limit
     *
     * @return ResponseVo
     */
    @ApiOperation(value = "获取最新的问题列表", notes = "返回最新的问题列表（分页查询结果）")
    @GetMapping(path = "/questions/latest")
    public BaseResponse<List<QuestionItemVO>> getLatestQuestions(@RequestParam(name = "offset") @Positive(message = "页码不能小于0") long offset,
                                                                 @RequestParam(name = "limit")
                                                                 @Min(value = 1, message = "页面大小不能小于1")
                                                                 @Max(value = 10, message = "页面大小不能大于10") long limit,
                                                                 @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        List<QuestionItemVO> questions = questionService.getLatestQuestions(offset, limit);
        //1. 处理用户之间的关系以及用户与问题之间关系(关注与点赞数)
        for (QuestionItemVO question : questions) {
            if (question.getType() == ItemType.answer) {
                //当前用户是否关注答题者
                question.getAnswer()
                        .getAuthor()
                        .setHasFollow(followService.isFollower(userInfo.getId(),
                                EntityType.MEMBER, question.getAnswer().getAuthor().getId()));
                //获取点赞数
                question.getAnswer().setVoteupCount((int) voteService.getVoteUpCount(EntityType.ANSWER
                        , question.getAnswer().getId()));
            } else if (question.getType() == ItemType.question) {
                //当前用户是否关注问题
                question.setHasFollow(followService.isFollower(userInfo.getId(),
                        EntityType.QUESTION, question.getId()));
            }
        }
        return BaseResponse.success(questions);
    }


}
