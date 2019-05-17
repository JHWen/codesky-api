package top.codesky.forcoder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Date: 2019/4/24 19:12
 * @Author: codesky
 * @Description: 登录后保存在session中的用户信息
 */
@Data
@AllArgsConstructor
public class UserInfo{
    private Long id;
    private String username;
}
