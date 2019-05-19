package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.EntityType;
import top.codesky.forcoder.common.constant.ResultEnum;
import top.codesky.forcoder.model.support.UserInfo;
import top.codesky.forcoder.model.vo.*;
import top.codesky.forcoder.service.FollowService;
import top.codesky.forcoder.service.UserService;

import java.util.List;

/**
 * @Date: 2019/5/13 17:40
 * @Author: codesky
 * @Description: 关注功能：关注用户或问题等
 */

@Api(tags = {"关注(问题、用户)功能接口"})
@RestController
@RequestMapping(path = "/api")
public class FollowController {
    private static final Logger loger = LoggerFactory.getLogger(FollowController.class);

    private final FollowService followService;

    private final UserService userService;


    @Autowired
    public FollowController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @ApiOperation(value = "关注问题", notes = "返回该问题的关注数")
    @PostMapping(path = "/question/{questionId}/follow")
    public ResponseVo followQuestion(@PathVariable("questionId") long questionId,
                                     @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        try {
            //todo:判断问题是否存在
            //1.判断是否关注，避免重复调用接口
            if (!followService.isFollower(userInfo.getId(), EntityType.QUESTION, questionId)) {
                if (followService.follow(userInfo.getId(), EntityType.QUESTION, questionId)) {
                    return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
                }
            }
            long followerCount = followService.getFollowerCount(EntityType.QUESTION, questionId);
            return ResponseVo.success(ResultEnum.SUCCESS, new FollowVo(followerCount));
        } catch (Exception e) {
            loger.error("关注问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    @ApiOperation(value = "取关问题", notes = "返回该问题的关注数")
    @DeleteMapping(path = "/question/{questionId}/follow")
    public ResponseVo unfollowQuestion(@PathVariable("questionId") long questionId,
                                       @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        try {
            //todo:判断问题是否存在
            //1.判断是否关注，避免错误调用接口
            if (followService.isFollower(userInfo.getId(), EntityType.QUESTION, questionId)) {
                if (followService.unfollow(userInfo.getId(), EntityType.QUESTION, questionId)) {
                    return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
                }
            }
            long followerCount = followService.getFollowerCount(EntityType.QUESTION, questionId);
            return ResponseVo.success(ResultEnum.SUCCESS, new FollowVo(followerCount));
        } catch (Exception e) {
            loger.error("取消关注问题失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    @ApiOperation(value = "关注用户", notes = "返回该用户的粉丝(关注)数")
    @PostMapping(path = "/member/{memberId}/follow")
    public ResponseVo followMember(@PathVariable("memberId") long memberId,
                                   @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        try {
            //todo:判断用户/会员是否存在
            //1.判断是否关注，避免重复调用接口
            if (!followService.isFollower(userInfo.getId(), EntityType.MEMBER, memberId)) {
                if (followService.follow(userInfo.getId(), EntityType.MEMBER, memberId)) {
                    return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
                }
            }
            long followerCount = followService.getFollowerCount(EntityType.MEMBER, memberId);
            return ResponseVo.success(ResultEnum.SUCCESS, new FollowVo(followerCount));
        } catch (Exception e) {
            loger.error("关注用户失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    @ApiOperation(value = "取关用户", notes = "返回该用户的粉丝(关注)数")
    @DeleteMapping(path = "/member/{memberId}/follow")
    public ResponseVo unfollowMember(@PathVariable("memberId") long memberId,
                                     @SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {
        try {
            //todo:判断用户/会员是否存在
            //1.判断是否关注，避免错误调用接口
            if (followService.isFollower(userInfo.getId(), EntityType.MEMBER, memberId)) {
                if (followService.unfollow(userInfo.getId(), EntityType.MEMBER, memberId)) {
                    return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
                }
            }
            long followerCount = followService.getFollowerCount(EntityType.MEMBER, memberId);
            return ResponseVo.success(ResultEnum.SUCCESS, new FollowVo(followerCount));
        } catch (Exception e) {
            loger.error("取消关注用户失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }


    /**
     * 获取粉丝列表
     *
     * @param offset
     * @param limit
     * @return
     */
    @ApiOperation(value = "获取该用户的粉丝列表", notes = "返回该用户的粉丝列表（分页查询）")
    @GetMapping(path = "/member/{memberId}/followers")
    public ResponseVo followersOfMember(@PathVariable("memberId") long memberId,
                                        @RequestParam("offset") long offset,
                                        @RequestParam("limit") long limit) {

        try {
            List<Long> idsOfFollowers = followService.getFollowers(EntityType.MEMBER, memberId, offset, limit);
            FollowerVo followerVo = new FollowerVo();
            //通过id,获取用户信息
            if (idsOfFollowers.size() > 0) {
                //去mysql数据库查询
                List<PublicationsOfMemberVO> followers = userService.getMembersByUserIds(idsOfFollowers);
                followerVo.setFollowers(followers);
            }
            return ResponseVo.success(ResultEnum.SUCCESS, followerVo);
        } catch (Exception e) {
            loger.error("获取粉丝列表失败:{}", e.getMessage());
        }
        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 获取关注者（我关注的人）列表
     *
     * @return
     */
    @ApiOperation(value = "获取该用户关注的人列表", notes = "返回该用户的关注的人列表（分页查询）")
    @GetMapping(path = "/member/{memberId}/followees")
    public ResponseVo followeesOfMember(@PathVariable("memberId") long memberId,
                                        @RequestParam("offset") long offset,
                                        @RequestParam("limit") long limit) {
        try {
            List<Long> idsOfFollowees = followService.getFollowees(memberId, EntityType.MEMBER, offset, limit);
            FolloweeVo followeeVo = new FolloweeVo();
            //通过id,获取用户信息
            if (idsOfFollowees.size() > 0) {
                //去mysql数据库查询
                List<PublicationsOfMemberVO> followers = userService.getMembersByUserIds(idsOfFollowees);
                followeeVo.setFollowees(followers);
            }
            return ResponseVo.success(ResultEnum.SUCCESS, followeeVo);
        } catch (Exception e) {
            loger.error("获取粉丝列表失败:{}", e.getMessage());
        }
        return ResponseVo.error(ResultEnum.INTERFACE_INNER_INVOKE_ERROR);
    }
}
