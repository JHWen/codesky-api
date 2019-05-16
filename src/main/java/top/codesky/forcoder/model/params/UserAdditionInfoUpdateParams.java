package top.codesky.forcoder.model.params;

import java.util.Date;

/**
 * @Date: 2019/5/16 11:05
 * @Author: codesky
 * @Description: 选择性更新用户的描述信息，封装请求参数,采用引用类型配合mybatis条件语句
 */
public class UserAdditionInfoUpdateParams {

    private Long userId;

    private String username;

    private Short gender;

    private String avatarUrl;

    private String headline;

    private String business;

    private Date gmtModified;

    public UserAdditionInfoUpdateParams() {
    }

    public UserAdditionInfoUpdateParams(Long userId, String username, Short gender, String avatarUrl, String headline, String business, Date gmtModified) {
        this.userId = userId;
        this.username = username;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.headline = headline;
        this.business = business;
        this.gmtModified = gmtModified;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "UserAdditionInfoUpdateParams{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", headline='" + headline + '\'' +
                ", business='" + business + '\'' +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
