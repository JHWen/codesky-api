package top.codesky.forcoder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.common.constant.RegisterResult;
import top.codesky.forcoder.common.constant.ResultEnum;
import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.model.params.LoginRequestParam;
import top.codesky.forcoder.model.params.UserAdditionInfoUpdateParam;
import top.codesky.forcoder.model.params.UserRegisterParam;
import top.codesky.forcoder.model.support.BaseResponse;
import top.codesky.forcoder.model.support.UserInfo;
import top.codesky.forcoder.model.vo.PublicationsOfMemberVO;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.BeanUtils;

import javax.validation.Valid;
import java.util.Date;

/**
 * @Date: 2019/4/20 11:38
 * @Author: codesky
 * @Description: 用户相关的控制层
 */
@Slf4j
@Api(tags = {"用户管理接口"})
@RestController
@RequestMapping(path = "/api")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 新用户注册
     *
     * @param userRegisterParam 封装用户注册信息
     * @return 注册结果
     */
    @ApiOperation(value = "用户注册", notes = "返回操作结果")
    @PostMapping(path = "/register")
    public ResponseEntity<BaseResponse> register(@RequestBody @Valid UserRegisterParam userRegisterParam) {

        RegisterResult result = userService.register(userRegisterParam);

        if (result == RegisterResult.SUCCESS) {
            return ResponseEntity.ok()
                    .body(BaseResponse.success(result.getMessage()));
        } else {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST, result.getMessage()));
        }
    }


    /**
     * 获取当前登录用户的个人信息
     *
     * @return 个人信息
     */
    @ApiOperation(value = "获取当前登录用户的个人信息", notes = "返回当前登录用户的个人信息")
    @GetMapping(path = "/me")
    public ResponseEntity<BaseResponse<PublicationsOfMemberVO>> getInfoAboutMe(@SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo) {

        UserAdditionInfo userAdditionInfo = userService.getUserAdditionInfo(userInfo.getId());

        if (userAdditionInfo == null) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR));
        }

        PublicationsOfMemberVO publicationsOfMemberVO = BeanUtils.copyPropertiesFrom(userAdditionInfo,
                PublicationsOfMemberVO.class);
        //注意id的区别
        publicationsOfMemberVO.setId(userAdditionInfo.getUserId());

        return ResponseEntity.ok()
                .body(BaseResponse.success(publicationsOfMemberVO));
    }

    /**
     * 获取其他用户的公开信息
     *
     * @param username 用户名
     * @return 用户的公开信息
     */
    @ApiOperation(value = "获取用户的公开个人信息", notes = "返回该用户的公开个人信息")
    @GetMapping(path = "/member/{username}/publications")
    public ResponseEntity<BaseResponse<PublicationsOfMemberVO>> getPublicationsOfMember(@PathVariable(name = "username") String username) {
        if (StringUtils.isEmpty(username)) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST,
                            ResultEnum.PARAM_IS_BLANK.message()));
        }

        UserAdditionInfo userAdditionInfo = userService.getPublicationsOfMember(username);
        if (userAdditionInfo == null) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error(HttpStatus.BAD_REQUEST,
                            ResultEnum.USER_NOT_EXIST.message()));
        }

        PublicationsOfMemberVO publicationsOfMemberVO = BeanUtils.copyPropertiesFrom(userAdditionInfo,
                PublicationsOfMemberVO.class);
        //注意id的区别
        publicationsOfMemberVO.setId(userAdditionInfo.getUserId());

        return ResponseEntity.ok(BaseResponse.success(publicationsOfMemberVO));
    }


    @ApiOperation(value = "更新用户个人介绍信息", notes = "返回更新结果")
    @PutMapping(path = "/me")
    public ResponseEntity<BaseResponse> updateAvatar(@SessionAttribute(Base.USER_INFO_SESSION_KEY) UserInfo userInfo,
                                                     @RequestBody UserAdditionInfoUpdateParam params) {
        log.debug("UserAdditionInfoUpdateParam:{}", params.toString());

        params.setUserId(userInfo.getId());
        params.setGmtModified(new Date());

        if (userService.updateUserAdditionInfo(params)) {
            return ResponseEntity.ok(BaseResponse.success());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ApiOperation(value = "用户登录", notes = "返回登录结果")
    @PostMapping(path = "/login")
    public BaseResponse<Object> login(@RequestBody LoginRequestParam loginRequestParam) {
        return BaseResponse.success(null);
    }

    @ApiOperation(value = "用户注销", notes = "返回注销结果")
    @PostMapping(path = "/logout")
    public BaseResponse<Object> logout() {
        return BaseResponse.success(null);
    }


}
