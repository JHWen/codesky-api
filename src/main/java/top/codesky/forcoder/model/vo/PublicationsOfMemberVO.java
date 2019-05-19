package top.codesky.forcoder.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用户的个人介绍/描述信息，即公开信息
 */
@Data
public class PublicationsOfMemberVO {
    private Long id;
    private String username;
    private short gender;
    private String avatarUrl;
    private String headline;
    private String business;
    private Date gmtCreate;
    private Date gmtModified;

    private boolean hasFollow;
}
