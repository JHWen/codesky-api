package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.constant.ItemType;
import top.codesky.forcoder.common.constant.ResultCodeEnum;
import top.codesky.forcoder.model.dto.UserInfo;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.vo.*;
import top.codesky.forcoder.service.AnswerService;
import top.codesky.forcoder.service.FollowService;
import top.codesky.forcoder.service.QuestionService;
import top.codesky.forcoder.service.VoteService;

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
     * @param questionRequestVo
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "添加问题，即提问", notes = "返回操作结果，包含问题的相关信息")
    @PostMapping(path = "/question")
    public ResponseVo addQuestion(@RequestBody QuestionAddVo questionRequestVo,
                                  @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {

        if (StringUtils.isEmpty(questionRequestVo.getTitle()) ||
                StringUtils.isEmpty(questionRequestVo.getContent()) || userInfo == null) {

            return ResponseVo.error(ResultCodeEnum.PARAM_NOT_COMPLETE);
        }

        try {

            Question question = questionService.addQuestion(questionRequestVo.getTitle(),
                    questionRequestVo.getContent(), userInfo.getId());

            if (question != null) {
                //todo: 添加问题，返回问题信息？ 是否在问题中封装提问者的信息?
                return ResponseVo.success(ResultCodeEnum.SUCCESS, question);
            }
        } catch (Exception e) {
            log.error("添加问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 获取问题的详细信息
     * 还要获取对应的回答数据
     *
     * @param questionId 问题id
     * @return ResponseVo
     */
    @ApiOperation(value = "获取对应问题的详细信息", notes = "返回问题的详细信息，包含问题的相关回答数据")
    @GetMapping(path = "/question/{questionId}")
    public ResponseVo getQuestionDetails(@PathVariable("questionId") long questionId,
                                         @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        if (questionId < 0) {
            return ResponseVo.error(ResultCodeEnum.PARAM_IS_INVALID);
        }

        try {
            //返回数据包括：问题信息，作者信息，回答信息（默认按时间降序排序）
            QuestionDetailsVo questionDetailsVo = questionService.getQuestionDetailsByQuestionId(questionId);

            if (questionDetailsVo != null) {
                //判断用户是否关注问题
                questionDetailsVo.setHasFollow(followService.isFollower(userInfo.getId(),
                        EntityType.QUESTION, questionDetailsVo.getId()));
                questionDetailsVo.getAuthor().setHasFollow(followService.isFollower(userInfo.getId(),
                        EntityType.MEMBER, questionDetailsVo.getAuthor().getId()));
                questionDetailsVo.setFollowerCount((int) followService.getFollowerCount(EntityType.QUESTION,
                        questionDetailsVo.getId()));

                // 获取回答列表
                List<AnswerDetailsVo> answers = answerService.getAnswersByQuestionId(questionDetailsVo.getId());
                //处理当前用户点赞关系以及用户之间的关注关系
                for(AnswerDetailsVo answer : answers) {
                    long voteUpCount = voteService.getVoteUpCount(EntityType.ANSWER, answer.getId());
                    answer.setVoteupCount((int) voteUpCount);
                    //判断用户之间的关注关系
                    answer.getAuthor().setHasFollow(followService.isFollower(userInfo.getId(),
                            EntityType.MEMBER, answer.getAuthor().getId()));
                }
                questionDetailsVo.setAnswers(answers);
                return ResponseVo.success(ResultCodeEnum.SUCCESS, questionDetailsVo);
            }
        } catch (Exception e) {
            log.error("获取问题详细信息失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
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
                return ResponseVo.success(ResultCodeEnum.SUCCESS);
            }
        } catch (Exception e) {
            log.error("删除问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 分页获取最新的问题List，按照时间倒序排列
     * 携带参数 offset , limit
     *
     * @return ResponseVo
     */
    @ApiOperation(value = "获取最新的问题列表", notes = "返回最新的问题列表（分页查询结果）")
    @GetMapping(path = "/questions/latest")
    public ResponseVo getLatestQuestions(@RequestParam(name = "offset") long offset,
                                         @RequestParam(name = "limit") long limit,
                                         @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        if (offset < 0 || limit <= 0 || limit > 10) {
            return ResponseVo.error(ResultCodeEnum.PARAM_IS_INVALID);
        }

        try {
            List<QuestionItemVo> questions = questionService.getLatestQuestions(offset, limit);
            if (questions != null) {
                log.debug("questionWithAuthors：{}", questions);
                //处理用户之间的关系以及用户与问题之间关系
                for(QuestionItemVo question : questions) {
                    if (question.getType() == ItemType.answer) {
                        //当前用户是否关注答题者
                        question.getAnswer()
                                .getAuthor()
                                .setHasFollow(followService.isFollower(userInfo.getId(),
                                        EntityType.MEMBER, question.getAnswer().getAuthor().getId()));
                    } else if (question.getType() == ItemType.question) {
                        //当前用户是否关注问题
                        question.setHasFollow(followService.isFollower(userInfo.getId(),
                                EntityType.QUESTION, question.getId()));
                    }
                }
                return ResponseVo.success(ResultCodeEnum.SUCCESS, questions);
            }
        } catch (Exception e) {
            log.error("获取问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }


}
