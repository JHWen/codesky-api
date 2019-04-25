package top.codesky.forcoder.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.codesky.forcoder.dao.UserMapper;
import top.codesky.forcoder.model.entity.UserForAuthentication;
import top.codesky.forcoder.model.vo.LoginRequestVo;
import top.codesky.forcoder.model.vo.ResponseVo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2019/4/10 10:05
 * @Author: codesky
 * @Description: 测试控制层
 */
@RestController
@RequestMapping("/api")
public class TestController {

    private final UserMapper userDao;

    @Autowired
    public TestController(UserMapper userDao) {
        this.userDao = userDao;
    }

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
    public UserForAuthentication getUser(@PathVariable("name") String username) {
        return userDao.findByUsername(username);
    }


    @PostMapping(path = "/login")
    public ResponseVo login(@RequestBody LoginRequestVo loginRequestVo) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(200);
        responseVo.setMsg("success");
        return responseVo;
    }
}
