package top.codesky.forcoder.model.vo;

import top.codesky.forcoder.model.entity.UserAdditionInfo;

import java.util.Date;

/**
 * 用户的个人介绍/描述信息，即公开信息
 */
public class PublicationsOfMemberVo {
    private Long id;
    private String username;
    private short gender;
    private String avatarUrl;
    private String headline;
    private String business;
    private Date gmtCreated;
    private Date gmtModified;

    public PublicationsOfMemberVo() {
    }

    public PublicationsOfMemberVo(Long id, short gender, String avatarUrl, String headline, String business, Date gmtCreated, Date gmtModified) {
        this.id = id;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.headline = headline;
        this.business = business;
        this.gmtCreated = gmtCreated;
        this.gmtModified = gmtModified;
    }

    public PublicationsOfMemberVo(UserAdditionInfo userAdditionInfo) {
        this.id = userAdditionInfo.getUserId();
        this.username = userAdditionInfo.getUsername();
        this.gender = userAdditionInfo.getGender();
        this.avatarUrl = userAdditionInfo.getAvatarUrl();
        this.headline = userAdditionInfo.getHeadline();
        this.business = userAdditionInfo.getBusiness();
        this.gmtCreated = userAdditionInfo.getGmtCreated();
        this.gmtModified = userAdditionInfo.getGmtModified();
    }

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

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
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

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "PublicationsOfMemberVo{" +
                "id=" + id +
                ", gender=" + gender +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", headline='" + headline + '\'' +
                ", business='" + business + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
