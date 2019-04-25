package top.codesky.forcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.model.other.UserInfo;
import top.codesky.forcoder.model.vo.AnswerAddVo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.AnswerService;
import top.codesky.forcoder.util.Constants;

import javax.servlet.http.HttpSession;

/**
 * @Date: 2019/4/20 12:05
 * @Author: codesky
 * @Description: 回答相关的控制层
 */
@RestController
@RequestMapping(path = "/api")
public class AnswerController {
    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(path = "/answer")
    public ResponseVo addAnswer(@RequestBody AnswerAddVo answerAddVo, HttpSession httpSession) {
        ResponseVo responseVo = new ResponseVo();
        if (answerAddVo.getQuestionId() == null || StringUtils.isEmpty(answerAddVo.getContent())) {
            responseVo.setCode(600);
            responseVo.setMsg("回答内容为空");
            return responseVo;
        }

        try {
            UserInfo userInfo = (UserInfo) httpSession.getAttribute(Constants.USER_SESSION_TOKEN);
            if (answerService.addAnswer(answerAddVo.getQuestionId(), userInfo.getId()
                    , answerAddVo.getContent())) {
                responseVo.setCode(200);
                responseVo.setMsg("添加回答成功");
                return responseVo;
            }
        } catch (Exception e) {
            logger.error("添加回答失败：{}", e.getMessage());
        }

        responseVo.setCode(600);
        responseVo.setMsg("添加回答失败");
        return responseVo;
    }

    /**
     * 获取回答的详情
     *
     * @param answerId
     * @return
     */
    @GetMapping(path = "/answer/{answer_id}")
    public ResponseVo getAnswer(@PathVariable("answer_id") long answerId) {
        ResponseVo responseVo = new ResponseVo();

        try {

        } catch (Exception e) {
            logger.error("添加回答失败：{}", e.getMessage());
        }

        responseVo.setCode(600);
        responseVo.setMsg("添加回答失败");
        return responseVo;
    }

}
