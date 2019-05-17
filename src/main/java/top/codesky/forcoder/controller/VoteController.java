package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.constant.ResultCodeEnum;
import top.codesky.forcoder.common.constant.VoteStatus;
import top.codesky.forcoder.model.dto.UserInfo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.model.vo.VotePostVo;
import top.codesky.forcoder.model.vo.VoteResult;
import top.codesky.forcoder.service.VoteService;

/**
 * @Date: 2019/5/12 14:42
 * @Author: codesky
 * @Description: 点赞、点赞控制层
 */
@Api(tags = {"赞踩功能接口"})
@RestController
@RequestMapping(path = "/api")
public class VoteController {
    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * 对回答点赞、点踩
     *
     * @param answerId
     * @param votePostVo
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "对回答点赞、点踩或保持中立", notes = "返回当前回答的点赞数")
    @PostMapping(path = "/answer/{answerId}/vote")
    public ResponseVo voteAnswer(@PathVariable("answerId") long answerId,
                                 @RequestBody VotePostVo votePostVo,
                                 @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        try {
            VoteResult voteResult = new VoteResult();

            switch (votePostVo.getType()) {
                case "up": {
                    long voteCount = voteService.voteUp(userInfo.getId(), EntityType.ANSWER, answerId);
                    voteResult.setVoting(VoteStatus.VOTEUP.toString());
                    voteResult.setVoteCount(voteCount);
                    break;
                }
                case "down": {
                    long voteCount = voteService.voteDown(userInfo.getId(), EntityType.ANSWER, answerId);
                    voteResult.setVoting(VoteStatus.VOTEDOWN.toString());
                    voteResult.setVoteCount(voteCount);
                    break;
                }
                case "neutral": {
                    long voteCount = voteService.voteNeutral(userInfo.getId(), EntityType.ANSWER, answerId);
                    voteResult.setVoting(VoteStatus.NEUTRALITY.toString());
                    voteResult.setVoteCount(voteCount);
                    break;
                }
                default:
                    return ResponseVo.error(ResultCodeEnum.PARAM_TYPE_BIND_ERROR);
            }

            return ResponseVo.success(ResultCodeEnum.SUCCESS, voteResult);
        } catch (Exception e) {
            logger.error("投票失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

}
