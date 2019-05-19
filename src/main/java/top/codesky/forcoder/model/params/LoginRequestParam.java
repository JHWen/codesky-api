package top.codesky.forcoder.model.params;

import lombok.Data;

/**
 * @Date: 2019/4/18 10:29
 * @Author: codesky
 * @Description: 封装登陆请求JSON数据中的用户登录相关参数
 */
@Data
public class LoginRequestParam {
    private String username;
    private String password;
}
