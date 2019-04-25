package top.codesky.forcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.model.entity.User;
import top.codesky.forcoder.model.other.PublicationsOfMember;
import top.codesky.forcoder.model.other.UserInfo;
import top.codesky.forcoder.model.vo.InfoOfMeVo;
import top.codesky.forcoder.model.vo.RegisterUserVo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.Constants;

import javax.servlet.http.HttpSession;

/**
 * @Date: 2019/4/20 11:38
 * @Author: codesky
 * @Description: 用户相关的控制层
 */
@RestController
@RequestMapping(path = "/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * 登录成功后，获取个人信息
     *
     * @return 个人信息
     */
    @GetMapping(path = "/me")
    public ResponseVo getUserInfo(HttpSession session) {
        ResponseVo responseVo = new ResponseVo();
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.USER_SESSION_TOKEN);
        if (StringUtils.isEmpty(userInfo.getUsername())) {
            responseVo.setCode(600);
            responseVo.setMsg("用户未登录");
            return responseVo;
        }

        User user = userService.getUserInfo(userInfo.getUsername());
        if (user == null) {
            responseVo.setCode(600);
            responseVo.setMsg("用户登录状态异常");
            return responseVo;
        }

        InfoOfMeVo infoOfMeVo = new InfoOfMeVo();
        infoOfMeVo.setId(user.getId());
        infoOfMeVo.setName(user.getUsername());
        infoOfMeVo.setGender(user.getGender());
        infoOfMeVo.setAvatarUrl(user.getAvatarUrl());

        responseVo.setCode(200);
        responseVo.setMsg("success");
        responseVo.setData(infoOfMeVo);

        return responseVo;
    }

    /**
     * 获取其他用户的公开信息
     *
     * @param username 用户名
     * @return 用户的公开信息
     */
    @GetMapping(path = "/member/{username}/publications")
    public ResponseVo getPublicationsOfMember(@PathVariable(name = "username") String username) {
        ResponseVo responseVo = new ResponseVo();
        try {
            if (!StringUtils.isEmpty(username)) {
                PublicationsOfMember publicationsOfMember = userService.getPublicationsOfMember(username);
                responseVo.setCode(200);
                responseVo.setMsg("获取用户公开信息成功");
                responseVo.setData(publicationsOfMember);
                return responseVo;
            }
        } catch (Exception e) {
            logger.error("获取用户公开信息失败：{}", e.getMessage());
        }
        responseVo.setCode(600);
        responseVo.setMsg("获取用户公开信息失败");
        return responseVo;
    }

    /**
     * 新用户注册
     *
     * @param registerUserVo 封装用户注册信息 bean
     * @return 注册结果
     */
    @PostMapping(path = "/register")
    public ResponseVo register(@RequestBody RegisterUserVo registerUserVo) {
        ResponseVo responseVo = new ResponseVo();

        if (StringUtils.isEmpty(registerUserVo.getUsername()) ||
                StringUtils.isEmpty(registerUserVo.getPassword())) {
            responseVo.setCode(600);
            responseVo.setMsg("用户名或密码不能为空");
            return responseVo;
        }

        try {
            responseVo = userService.register(registerUserVo.getUsername(), registerUserVo.getPassword());
            return responseVo;
        } catch (Exception e) {
            logger.error("注册失败：{}", e.getMessage());
        }

        responseVo.setCode(600);
        responseVo.setMsg("注册失败");
        return responseVo;
    }

}
