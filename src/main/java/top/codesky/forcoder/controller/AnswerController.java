package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.ResultEnum;
import top.codesky.forcoder.model.support.UserInfo;
import top.codesky.forcoder.model.vo.AnswerAddVo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.AnswerService;

/**
 * @Date: 2019/4/20 12:05
 * @Author: codesky
 * @Description: 回答相关的控制层
 */
@Api(tags = {"问题回答功能接口"})
@RestController
@RequestMapping(path = "/api")
public class AnswerController {
    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * 添加回答
     *
     * @param answerAddVo
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "添加回答", notes = "返回操作结果")
    @PostMapping(path = "/answer")
    public ResponseVo addAnswer(@RequestBody AnswerAddVo answerAddVo,
                                @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        if (answerAddVo.getQuestionId() == null || StringUtils.isEmpty(answerAddVo.getContent())) {
            return ResponseVo.error(ResultEnum.PARAM_IS_INVALID);
        }

        try {
            //todo:判断用户是否已经回答

            if (answerService.addAnswer(answerAddVo.getQuestionId(), userInfo.getId()
                    , answerAddVo.getContent())) {
                return ResponseVo.success(ResultEnum.SUCCESS);
            }

        } catch (Exception e) {
            logger.error("添加回答失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 获取回答的详情
     *
     * @param answerId
     * @return
     */
    @ApiOperation(value = "获取回答的详细信息", notes = "返回回答的详细信息")
    @GetMapping(path = "/answer/{answer_id}")
    public ResponseVo getAnswer(@PathVariable("answer_id") long answerId) {

        try {
            return ResponseVo.success(ResultEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("添加回答失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

}
