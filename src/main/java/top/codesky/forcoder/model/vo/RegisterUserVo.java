package top.codesky.forcoder.model.vo;

/**
 * @Date: 2019/5/5 11:50
 * @Author: codesky
 * @Description: 前端传参 -> 封装用户注册信息
 */
public class RegisterUserVo {
    private String username;
    private String password;
    private String checkPassword;

    public RegisterUserVo() {
    }

    public RegisterUserVo(String username, String password, String checkPassword) {
        this.username = username;
        this.password = password;
        this.checkPassword = checkPassword;
    }

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

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    @Override
    public String toString() {
        return "RegisterUserVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", checkPassword='" + checkPassword + '\'' +
                '}';
    }
}
