package top.codesky.forcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.ResultCodeEnum;
import top.codesky.forcoder.model.dto.UserInfo;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.entity.QuestionWithAuthor;
import top.codesky.forcoder.model.vo.*;
import top.codesky.forcoder.service.AnswerService;
import top.codesky.forcoder.service.QuestionService;

import java.util.List;

/**
 * @Date: 2019/4/20 12:04
 * @Author: codesky
 * @Description: 问题相关的控制层
 */
@RestController
@RequestMapping(path = "/api")
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    /**
     * 添加问题
     *
     * @param questionRequestVo
     * @param userInfo
     * @return
     */
    @PostMapping(path = "/question")
    public ResponseVo addQuestion(@RequestBody QuestionAddVo questionRequestVo,
                                  @SessionAttribute(Base.USER_INFO_SESSION_TKEY) UserInfo userInfo) {

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
            logger.error("添加问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 获取问题的详细信息
     * 还要获取对应的回答数据
     *
     * @param questionId
     * @return
     */
    @GetMapping(path = "/question/{questionId}")
    public ResponseVo getQuestionDetails(@PathVariable("questionId") long questionId) {
        if (questionId < 0) {
            return ResponseVo.error(ResultCodeEnum.PARAM_IS_INVALID);
        }

        try {
            //返回数据包括：问题信息，作者信息，回答信息（默认按时间降序排序）
            QuestionDetailsVo questionDetailsVo = questionService.getQuestionDetailsByQuestionId(questionId);

            if (questionDetailsVo != null) {
                // 获取回答列表
                List<AnswerDetailsVo> answers = answerService.getAnswersByQuestionId(questionDetailsVo.getId());
                questionDetailsVo.setAnswers(answers);
                return ResponseVo.success(ResultCodeEnum.SUCCESS, questionDetailsVo);
            }
        } catch (Exception e) {
            logger.error("获取问题详细信息失败：{}", e.getMessage());
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
    @DeleteMapping(path = "/question/{questionId}")
    public ResponseVo deleteQuestion(@PathVariable("questionId") long questionId,
                                     @SessionAttribute(Base.USER_INFO_SESSION_TKEY) UserInfo userInfo) {

        try {
            if (questionService.deleteQuestion(questionId, userInfo.getId())) {
                return ResponseVo.success(ResultCodeEnum.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("删除问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 获取最新的问题List，根据时间倒序排列，涉及到分页查询
     * 携带参数 offset limit
     *
     * @return
     */
    @GetMapping(path = "/questions/latest")
    public ResponseVo getLatestQuestions(@RequestParam(name = "offset") long offset,
                                         @RequestParam(name = "limit") long limit) {
        ResponseVo responseVo = new ResponseVo();
        if (offset < 0 || limit <= 0 || limit > 10) {
            return ResponseVo.error(ResultCodeEnum.PARAM_IS_INVALID);
        }

        try {
            List<QuestionWithAuthor> questionWithAuthors = questionService.getLatestQuestions(offset, limit);
            if (questionWithAuthors != null) {
                logger.debug("questionWithAuthors：{}", questionWithAuthors);
                return ResponseVo.success(ResultCodeEnum.SUCCESS, questionWithAuthors);
            }
        } catch (Exception e) {
            logger.error("获取问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }


}
