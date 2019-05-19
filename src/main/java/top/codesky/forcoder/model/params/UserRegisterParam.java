package top.codesky.forcoder.model.params;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Date: 2019/5/18 15:57
 * @Author: codesky
 * @Description: 封装用户注册请求参数
 */
@Data
public class UserRegisterParam {
    @NotEmpty(message = "用户名不能为空")
    @Size(max = 20, message = "用户名字符长度不能超过{max}")
    private String username;

    @NotEmpty(message = "用户密码不能为空")
    @Size(min = 6, max = 100, message = "用户密码字符长度范围为{min}到{max}之间")
    private String password;

    @NotEmpty(message = "用户确认密码不能为空")
    private String checkPassword;
}
