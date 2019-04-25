package top.codesky.forcoder.model.other;

/**
 * @Date: 2019/4/24 19:49
 * @Author: codesky
 * @Description: 用户的公开信息，如用户名、头像等
 */
public class PublicationsOfMember {
    private Long id;
    private String username;
    private String avatarUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "PublicationsOfMember{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
