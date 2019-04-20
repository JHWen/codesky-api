package top.codesky.forcoder.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date: 2019/4/20 11:38
 * @Author: codesky
 * @Description: 用户相关的控制层
 */
@RestController
@RequestMapping(path = "/api")
public class UserController {


    /**
     * 登录成功后，获取个人信息
     *
     * @return 个人信息
     */
    @GetMapping(path = "/me")
    public Object getUserInfo(HttpServletRequest request) {
        return null;
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
    public Object register() {

        return null;
    }

}
