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
import top.codesky.forcoder.model.support.BaseResponse;
import top.codesky.forcoder.model.support.UserInfo;
import top.codesky.forcoder.model.vo.*;
import top.codesky.forcoder.service.FollowService;
import top.codesky.forcoder.service.QuestionService;
import top.codesky.forcoder.service.UserService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @Date: 2019/5/13 17:40
 * @Author: codesky
 * @Description: 关注功能：关注用户或问题等
 */

@Api(tags = {"关注(问题、用户)功能接口"})
@RestController
@RequestMapping(path = "/api")
@Slf4j
public class FollowController {

    private final FollowService followService;
    private final UserService userService;
    private final QuestionService questionService;

    @Autowired
    public FollowController(FollowService followService, UserService userService, QuestionService questionService) {
        this.followService = followService;
        this.userService = userService;
        this.questionService = questionService;
    }

    @ApiOperation(value = "关注问题", notes = "返回该问题的关注数")
    @PostMapping(path = "/question/{questionId}/follow")
    public ResponseEntity<BaseResponse<FollowVO>> followQuestion(@PathVariable("questionId") @Positive long questionId,
                                                                 @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        // 1.判断问题是否存在
        if (!questionService.questionHasExisted(questionId)) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST));
        }

        // 2.判断是否关注，避免重复调用接口
        if (!followService.isFollower(userInfo.getId(), EntityType.QUESTION, questionId)) {
            if (!followService.follow(userInfo.getId(), EntityType.QUESTION, questionId)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR));
            }
        }
        long followerCount = followService.getFollowerCount(EntityType.QUESTION, questionId);
        return ResponseEntity.ok(BaseResponse.success(new FollowVO(followerCount)));
    }

    @ApiOperation(value = "取关问题", notes = "返回该问题的关注数")
    @DeleteMapping(path = "/question/{questionId}/follow")
    public ResponseEntity<BaseResponse<FollowVO>> unfollowQuestion(@PathVariable("questionId") long questionId,
                                                                   @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        // 1.判断问题是否存在
        if (!questionService.questionHasExisted(questionId)) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST));
        }

        // 2.判断是否关注，避免重复调用接口
        if (!followService.isFollower(userInfo.getId(), EntityType.QUESTION, questionId)) {
            if (!followService.unfollow(userInfo.getId(), EntityType.QUESTION, questionId)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR));
            }
        }
        long followerCount = followService.getFollowerCount(EntityType.QUESTION, questionId);
        return ResponseEntity.ok(BaseResponse.success(new FollowVO(followerCount)));
    }

    @ApiOperation(value = "关注用户", notes = "返回该用户的粉丝(关注)数")
    @PostMapping(path = "/member/{memberId}/follow")
    public ResponseEntity<BaseResponse<FollowVO>> followMember(@PathVariable("memberId") @Positive long memberId,
                                                               @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        // 1.判断用户是否存在
        if (!userService.userHasExisted(memberId)) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST));
        }

        // 2.判断是否关注，避免重复调用接口
        if (!followService.isFollower(userInfo.getId(), EntityType.MEMBER, memberId)) {
            if (!followService.follow(userInfo.getId(), EntityType.MEMBER, memberId)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR));
            }
        }
        long followerCount = followService.getFollowerCount(EntityType.MEMBER, memberId);
        return ResponseEntity.ok(BaseResponse.success(new FollowVO(followerCount)));
    }

    @ApiOperation(value = "取关用户", notes = "返回该用户的粉丝(关注)数")
    @DeleteMapping(path = "/member/{memberId}/follow")
    public ResponseEntity<BaseResponse<FollowVO>> unfollowMember(@PathVariable("memberId") @Positive long memberId,
                                                                 @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        // 1.判断用户是否存在
        if (!userService.userHasExisted(memberId)) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST));
        }

        // 2.判断是否关注，避免重复调用接口
        if (!followService.isFollower(userInfo.getId(), EntityType.MEMBER, memberId)) {
            if (!followService.unfollow(userInfo.getId(), EntityType.MEMBER, memberId)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR));
            }
        }
        long followerCount = followService.getFollowerCount(EntityType.MEMBER, memberId);
        return ResponseEntity.ok(BaseResponse.success(new FollowVO(followerCount)));
    }


    /**
     * 获取粉丝列表
     *
     * @param offset as pageNum
     * @param limit  as PageSize
     * @return BaseResponse<FollowerVO>
     */
    @ApiOperation(value = "获取该用户的粉丝列表", notes = "返回该用户的粉丝列表（分页查询）")
    @GetMapping(path = "/member/{memberId}/followers")
    public BaseResponse<FollowerVO> getFollowersOfMember(@PathVariable("memberId") @Positive long memberId,
                                                         @RequestParam("offset")
                                                         @Positive(message = "页号必须为正数") long offset,
                                                         @RequestParam("limit")
                                                         @Min(value = 1, message = "页码不能少于{min}")
                                                         @Max(value = 10, message = "页码不能大于{max}") long limit) {

        List<Long> idsOfFollowers = followService.getFollowers(EntityType.MEMBER, memberId, offset, limit);
        FollowerVO followerVO = new FollowerVO();
        //通过id,获取用户信息
        if (idsOfFollowers.size() > 0) {
            //去mysql数据库查询
            List<PublicationsOfMemberVO> followers = userService.getMembersByUserIds(idsOfFollowers);
            followerVO.setFollowers(followers);
        }
        return BaseResponse.success(followerVO);
    }

    /**
     * 获取关注者（我关注的人）列表
     *
     * @return BaseResponse<FollowerVO>
     */
    @ApiOperation(value = "获取该用户关注的人列表", notes = "返回该用户的关注的人列表（分页查询）")
    @GetMapping(path = "/member/{memberId}/followees")
    public BaseResponse<FolloweeVO> getFolloweesOfMember(@PathVariable("memberId") @Positive long memberId,
                                                         @RequestParam("offset")
                                                         @Positive(message = "页号必须为正数") long offset,
                                                         @RequestParam("limit")
                                                         @Min(value = 1, message = "页码不能少于{min}")
                                                         @Max(value = 10, message = "页码不能大于{max}") long limit) {
        List<Long> idsOfFollowees = followService.getFollowees(memberId, EntityType.MEMBER, offset, limit);
        FolloweeVO followeeVO = new FolloweeVO();
        //通过id,获取用户信息
        if (idsOfFollowees.size() > 0) {
            //去mysql数据库查询
            List<PublicationsOfMemberVO> followers = userService.getMembersByUserIds(idsOfFollowees);
            followeeVO.setFollowees(followers);
        }
        return BaseResponse.success(followeeVO);
    }
}
