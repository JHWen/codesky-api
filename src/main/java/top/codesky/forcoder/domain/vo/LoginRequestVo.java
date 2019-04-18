package top.codesky.forcoder.domain.vo;

/**
 * @Date: 2019/4/18 10:29
 * @Author: codesky
 * @Description: 封装登陆请求JSON数据中的用户相关参数
 */
public class LoginRequestVo {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequestVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
