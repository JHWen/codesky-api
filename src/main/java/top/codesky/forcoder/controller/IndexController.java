package top.codesky.forcoder.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.codesky.forcoder.dao.UserDao;
import top.codesky.forcoder.domain.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2019/4/10 10:05
 * @Author: codesky
 * @Description: forcoder
 */
@RestController
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private UserDao userDao;

    @ApiOperation(value = "管理员权限内容", notes = "返回json信息")
    @GetMapping(value = "/admin/content")
    public Map<String, Object> admin() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 200);
        resultMap.put("msg", "管理员权限内容");
        return resultMap;
    }

    @ApiOperation(value = "普通用户权限内容", notes = "返回json信息")
    @GetMapping(value = "/user/content")
    public Map<String, Object> member() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 200);
        resultMap.put("msg", "普通用户权限内容");
        return resultMap;
    }

    @ApiOperation(value = "首页内容", notes = "返回json信息")
    @GetMapping(value = "/index")
    public Map<String, Object> index() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 200);
        resultMap.put("msg", "首页内容");
        return resultMap;
    }

    @ApiOperation(value = "测试获取用户信息")
    @GetMapping(value = "/admin/{name}")
    public User getUser(@PathVariable("name") String username) {
        return userDao.findByUsername(username);
    }
}
