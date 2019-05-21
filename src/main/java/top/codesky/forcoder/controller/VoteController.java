package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.constant.VoteStatus;
import top.codesky.forcoder.model.support.BaseResponse;
import top.codesky.forcoder.model.support.UserInfo;
import top.codesky.forcoder.model.params.VotePostParam;
import top.codesky.forcoder.model.vo.VoteResultVO;
import top.codesky.forcoder.service.AnswerService;
import top.codesky.forcoder.service.VoteService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * @Date: 2019/5/12 14:42
 * @Author: codesky
 * @Description: 点赞、点赞控制层
 */
@Api(tags = {"赞踩功能接口"})
@RestController
@RequestMapping(path = "/api")
@Slf4j
public class VoteController {

    private final VoteService voteService;
    private final AnswerService answerService;

    @Autowired
    public VoteController(VoteService voteService, AnswerService answerService) {
        this.voteService = voteService;
        this.answerService = answerService;
    }

    /**
     * 对回答点赞、点踩
     *
     * @param answerId      answer id
     * @param votePostParam vote type up, down, neural
     * @param userInfo      userInfo in session
     * @return
     */
    @ApiOperation(value = "对回答点赞、点踩或保持中立", notes = "返回当前回答的点赞数")
    @PostMapping(path = "/answer/{answerId}/vote")
    public ResponseEntity<BaseResponse<VoteResultVO>> voteAnswer(@PathVariable("answerId") @Positive long answerId,
                                                                 @RequestBody @Valid VotePostParam votePostParam,
                                                                 @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        // 1.判断答案是否存在
        if (!answerService.answerHasExisted(answerId)) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST));
        }

        // 2.投票，即点赞 or 点踩
        VoteResultVO voteResultVO = new VoteResultVO();
        switch (votePostParam.getType()) {
            case VOTEUP: {
                long voteCount = voteService.voteUp(userInfo.getId(), EntityType.ANSWER, answerId);
                voteResultVO.setVoting(VoteStatus.VOTEUP.getValue());
                voteResultVO.setVoteCount(voteCount);
                break;
            }
            case VOTEDOWN: {
                long voteCount = voteService.voteDown(userInfo.getId(), EntityType.ANSWER, answerId);
                voteResultVO.setVoting(VoteStatus.VOTEDOWN.toString());
                voteResultVO.setVoteCount(voteCount);
                break;
            }
            case NEUTRALITY: {
                long voteCount = voteService.voteNeutral(userInfo.getId(), EntityType.ANSWER, answerId);
                voteResultVO.setVoting(VoteStatus.NEUTRALITY.getValue());
                voteResultVO.setVoteCount(voteCount);
                break;
            }
        }
        return ResponseEntity.ok(BaseResponse.success(voteResultVO));
    }

}
