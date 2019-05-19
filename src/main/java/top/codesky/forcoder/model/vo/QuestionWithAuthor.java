package top.codesky.forcoder.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Date: 2019/4/25 16:24
 * @Author: codesky
 * @Description: 包含提问者的回答数据
 */
@Getter
@Setter
public class QuestionWithAuthor {
    private Long id;

    private String title;

    private String content;

    private int answerCount;

    private int commentCount;

    private int followerCount;

    private Date gmtCreate;

    private Date gmtModified;

    private PublicationsOfMemberVO author;

}
