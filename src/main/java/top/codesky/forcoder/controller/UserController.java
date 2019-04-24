package top.codesky.forcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.model.entity.User;
import top.codesky.forcoder.model.vo.InfoOfMeVo;
import top.codesky.forcoder.model.vo.RegisterUserVo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.Constants;

import javax.servlet.http.HttpServletRequest;
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
        String username = (String) session.getAttribute(Constants.USER_SESSION_TOKEN);
        if (StringUtils.isEmpty(username)) {
            responseVo.setCode(600);
            responseVo.setMsg("用户未登录");
            return responseVo;
        }

        User user = userService.getUserInfo(username);
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
    public Object getPublicationsOfMember(@PathVariable(name = "username") String username) {
        return null;
    }

    /**
     * 新用户注册
     *
     * @return 返回注册结果
     */
    @PostMapping(path = "/register")
    public ResponseVo register(@RequestBody RegisterUserVo registerUserVo) {
        ResponseVo responseVo = new ResponseVo();

        if (StringUtils.isEmpty(registerUserVo.getUsername())) {
            responseVo.setCode(600);
            responseVo.setMsg("用户名不能为空");
        }

        if (StringUtils.isEmpty(registerUserVo.getPassword())) {
            responseVo.setCode(600);
            responseVo.setMsg("密码不能为空");
        }

        responseVo = userService.register(registerUserVo.getUsername(), registerUserVo.getPassword());

        return responseVo;
    }

}
