package top.codesky.forcoder.model.other;

/**
 * @Date: 2019/4/24 19:12
 * @Author: codesky
 * @Description: 登录后保存在session中的用户信息
 */
public class UserInfo {
    private long id;
    private String username;

    public UserInfo() {
    }

    public UserInfo(long id, String username) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
