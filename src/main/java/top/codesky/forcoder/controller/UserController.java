package top.codesky.forcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.Constants;
import top.codesky.forcoder.common.ResultCodeEnum;
import top.codesky.forcoder.model.dto.UserInfo;
import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.model.vo.PublicationsOfMemberVo;
import top.codesky.forcoder.model.vo.RegisterUserVo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.UserService;

/**
 * @Date: 2019/4/20 11:38
 * @Author: codesky
 * @Description: 用户相关的控制层
 */
@RestController
@RequestMapping(path = "/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 登录成功后，获取个人信息
     *
     * @return 个人信息
     */
    @GetMapping(path = "/me")
    public ResponseVo getInfoAboutMe(@SessionAttribute(name = Constants.USER_INFO_SESSION_TKEY)
                                             UserInfo userInfo) {

        if (userInfo == null || StringUtils.isEmpty(userInfo.getUsername())
                || userInfo.getId() == null) {
            return ResponseVo.error(ResultCodeEnum.USER_NOT_LOGGED_IN);
        }

        try {
            UserAdditionInfo userAdditionInfo = userService.getUserAdditionInfo(userInfo.getId());
            if (userAdditionInfo == null) {
                return ResponseVo.error(ResultCodeEnum.USER_NOT_EXIST);
            }

            PublicationsOfMemberVo infoOfMeVo = new PublicationsOfMemberVo(userAdditionInfo);

            return ResponseVo.success(ResultCodeEnum.SUCCESS, infoOfMeVo);
        } catch (Exception e) {
            logger.error("获取用户个人信息失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 获取其他用户的公开信息
     *
     * @param username 用户名
     * @return 用户的公开信息
     */
    @GetMapping(path = "/member/{username}/publications")
    public ResponseVo getPublicationsOfMember(@PathVariable(name = "username") String username) {
        if (StringUtils.isEmpty(username)) {
            return ResponseVo.error(ResultCodeEnum.PARAM_IS_BLANK);
        }

        try {
            UserAdditionInfo userAdditionInfo = userService.getPublicationsOfMember(username);
            if (userAdditionInfo == null) {
                return ResponseVo.error(ResultCodeEnum.USER_NOT_EXIST);
            }

            PublicationsOfMemberVo publicationsOfMemberVo = new PublicationsOfMemberVo(userAdditionInfo);

            return ResponseVo.success(ResultCodeEnum.SUCCESS, publicationsOfMemberVo);
        } catch (Exception e) {
            logger.error("获取用户公开信息失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
    }

    /**
     * 新用户注册
     *
     * @param registerUserVo 封装用户注册信息 bean
     * @return 注册结果
     */
    @PostMapping(path = "/register")
    public ResponseVo register(@RequestBody RegisterUserVo registerUserVo) {

        if (StringUtils.isEmpty(registerUserVo.getUsername()) ||
                StringUtils.isEmpty(registerUserVo.getPassword()) ||
                StringUtils.isEmpty(registerUserVo.getCheckPassword())) {
            return ResponseVo.error(ResultCodeEnum.USER_NAME_OR_PASSWORD_IS_EMPTY);
        }

        if (!registerUserVo.getPassword().equals(registerUserVo.getCheckPassword())) {
            return ResponseVo.error(ResultCodeEnum.USER_ENTERED_PASSWORD_DIFFER);
        }

        try {
            return userService.register(registerUserVo.getUsername(), registerUserVo.getPassword());
        } catch (Exception e) {
            logger.error("注册失败：{}", e.getMessage());
        }

        return ResponseVo.error(ResultCodeEnum.USER_REGISTER_ERROR);
    }

}
