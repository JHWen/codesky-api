package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.ProcessStatusEnum;
import top.codesky.forcoder.common.constant.ResultEnum;
import top.codesky.forcoder.model.support.BaseResponse;
import top.codesky.forcoder.model.support.UserInfo;
import top.codesky.forcoder.model.params.AnswerAddParam;
import top.codesky.forcoder.model.vo.AnswerDetailsVO;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.AnswerService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

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
     * @param answerAddParam AnswerAddParam contains questionId and answer content
     * @param userInfo       userinfo in session
     * @return ResponseEntity<BaseResponse>
     */
    @ApiOperation(value = "添加回答", notes = "返回操作结果")
    @PostMapping(path = "/answer")
    public ResponseEntity<BaseResponse> addAnswer(@RequestBody @Valid AnswerAddParam answerAddParam,
                                                  @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        ProcessStatusEnum processStatusEnum = answerService.addAnswer(answerAddParam.getQuestionId(),
                userInfo.getId(), answerAddParam.getContent());
        switch (processStatusEnum) {
            case SUCCESS:
                return ResponseEntity.ok(BaseResponse.success());
            case RECORD_HAS_EXISTED:
                return ResponseEntity.badRequest()
                        .body(BaseResponse.error(HttpStatus.BAD_REQUEST, "请勿重复回答"));
            default:
                return ResponseEntity.badRequest()
                        .body(BaseResponse.error(HttpStatus.BAD_REQUEST, processStatusEnum.message()));
        }
    }

    /**
     * 获取回答的详情
     *
     * @param answerId answer id
     * @return <code> ResponseEntity<BaseResponse<AnswerDetailsVO>> </code>
     */
    @ApiOperation(value = "获取回答的详细信息", notes = "返回回答的详细信息")
    @GetMapping(path = "/answer/{answer_id}")
    public ResponseEntity<BaseResponse<AnswerDetailsVO>> getAnswer(@PathVariable("answer_id") @Positive long answerId) {

        AnswerDetailsVO answerDetailsVO = answerService.getAnswerDetailsByAnswerId(answerId);
        if (answerDetailsVO != null) {
            return ResponseEntity.ok(BaseResponse.success(answerDetailsVO));
        }

        return ResponseEntity.badRequest()
                .body(BaseResponse.error(HttpStatus.BAD_REQUEST));
    }

}
