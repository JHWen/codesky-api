package top.codesky.forcoder.model.params;

import lombok.Data;

import java.util.Date;

/**
 * @Date: 2019/5/16 11:05
 * @Author: codesky
 * @Description: 选择性更新用户的描述信息，封装请求参数,采用引用类型配合mybatis条件语句
 */
@Data
public class UserAdditionInfoUpdateParam {
    private Long userId;
    private String username;
    private Short gender;
    private String avatarUrl;
    private String headline;
    private String business;
    private Date gmtModified;

}
