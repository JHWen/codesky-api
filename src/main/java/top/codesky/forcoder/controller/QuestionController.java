package top.codesky.forcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.model.entity.Question;
import top.codesky.forcoder.model.entity.QuestionWithAuthor;
import top.codesky.forcoder.model.other.PublicationsOfMember;
import top.codesky.forcoder.model.other.UserInfo;
import top.codesky.forcoder.model.vo.QuestionDetailsVo;
import top.codesky.forcoder.model.vo.QuestionAddVo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.QuestionService;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.Constants;

import javax.servlet.http.HttpSession;
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
    private final UserService userService;

    @Autowired
    public QuestionController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    /**
     * 添加问题
     *
     * @param questionRequestVo
     * @param httpSession
     * @return
     */
    @PostMapping(path = "/question")
    public ResponseVo addQuestion(@RequestBody QuestionAddVo questionRequestVo, HttpSession httpSession) {
        ResponseVo responseVo = new ResponseVo();
        try {
            if (StringUtils.isEmpty(questionRequestVo.getTitle()) ||
                    StringUtils.isEmpty(questionRequestVo.getContent())) {
                responseVo.setCode(600);
                responseVo.setMsg("添加的问题为空");
                return responseVo;
            }
            UserInfo userInfo = (UserInfo) httpSession.getAttribute(Constants.USER_SESSION_TOKEN);

            Question question = questionService.addQuestion(questionRequestVo.getTitle(),
                    questionRequestVo.getContent(), userInfo.getId());

            if (question != null) {
                responseVo.setCode(200);
                responseVo.setMsg("添加问题成功");

                //todo: 是否在问题中封装提问者的信息?
                responseVo.setData(question);
                return responseVo;
            }
        } catch (Exception e) {
            logger.error("添加问题失败：{}", e.getMessage());
        }
        responseVo.setCode(600);
        responseVo.setMsg("添加问题失败");
        return responseVo;
    }

    /**
     * 获取问题的详细信息
     *
     * @param questionId
     * @return
     */
    @GetMapping(path = "/question/{questionId}")
    public ResponseVo getQuestionDetails(@PathVariable("questionId") long questionId) {
        ResponseVo responseVo = new ResponseVo();
        try {
            Question question = questionService.getQuestionById(questionId);
            if (question != null) {
                // 获取提问者信息
                PublicationsOfMember author = userService.getPublicationsOfMember(question.getAuthorId());
                if (author != null) {
                    QuestionDetailsVo questionDetailsVo = new QuestionDetailsVo(question);
                    questionDetailsVo.setAuthor(author);
                    responseVo.setCode(200);
                    responseVo.setMsg("获取问题详细信息成功");
                    responseVo.setData(questionDetailsVo);
                }
            }
        } catch (Exception e) {
            logger.error("获取问题详细信息失败：{}", e.getMessage());
        }
        responseVo.setCode(600);
        responseVo.setMsg("获取问题详细信息失败");
        return responseVo;
    }

    /**
     * 删除问题
     *
     * @param questionId
     * @param httpSession
     * @return
     */
    @DeleteMapping(path = "/question/{questionId}")
    public ResponseVo deleteQuestion(@PathVariable("questionId") long questionId, HttpSession httpSession) {
        ResponseVo responseVo = new ResponseVo();
        try {
            UserInfo userInfo = (UserInfo) httpSession.getAttribute(Constants.USER_SESSION_TOKEN);
            if (questionService.deleteQuestion(questionId, userInfo.getId())) {
                responseVo.setCode(200);
                responseVo.setMsg("删除问题成功");
                return responseVo;
            }
        } catch (Exception e) {
            logger.error("删除问题失败：{}", e.getMessage());
        }
        responseVo.setCode(600);
        responseVo.setMsg("删除问题失败");
        return responseVo;
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
            responseVo.setCode(600);
            responseVo.setMsg("offset或limit参数错误");
            return responseVo;
        }

        try {
            List<QuestionWithAuthor> questionWithAuthors = questionService.getLatestQuestions(offset, limit);
            if (questionWithAuthors != null) {
                logger.debug("questionWithAuthors：{}", questionWithAuthors);
                responseVo.setCode(200);
                responseVo.setMsg("获取问题成功");
                responseVo.setData(questionWithAuthors);
                return responseVo;
            }
        } catch (Exception e) {
            logger.error("获取问题失败：{}", e.getMessage());
        }

        responseVo.setCode(600);
        responseVo.setMsg("获取问题失败");

        return responseVo;
    }


}
