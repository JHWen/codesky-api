package top.codesky.forcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.ResultCodeEnum;
import top.codesky.forcoder.model.dto.UserInfo;
import top.codesky.forcoder.model.vo.AnswerAddVo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.AnswerService;

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

    /**H
     * 添加回答
     *
     * @param answerAddVo
     * @param userInfo
     * @return
     */
    @PostMapping(path = "/answer")
    public ResponseVo addAnswer(@RequestBody AnswerAddVo answerAddVo,
                                @SessionAttribute(Base.USER_INFO_SESSION_TKEY) UserInfo userInfo) {
        if (answerAddVo.getQuestionId() == null || StringUtils.isEmpty(answerAddVo.getContent())) {
            return ResponseVo.error(ResultCodeEnum.PARAM_IS_INVALID);
        }

        try {
            if (answerService.addAnswer(answerAddVo.getQuestionId(), userInfo.getId()
                    , answerAddVo.getContent())) {
                return ResponseVo.success(ResultCodeEnum.SUCCESS);
            }

        } catch (Exception e) {
            logger.error("添加回答失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 获取回答的详情
     *
     * @param answerId
     * @return
     */
    @GetMapping(path = "/answer/{answer_id}")
    public ResponseVo getAnswer(@PathVariable("answer_id") long answerId) {

        try {
            return ResponseVo.success(ResultCodeEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("添加回答失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

}
