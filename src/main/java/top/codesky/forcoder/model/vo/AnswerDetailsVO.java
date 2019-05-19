package top.codesky.forcoder.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Date: 2019/5/7 19:19
 * @Author: codesky
 * @Description: 返回给前端的回答的信息
 */
@Data
public class AnswerDetailsVO {
    private Long id;
    private String content;
    private String excerpt;
    private int commentCount;
    private int voteupCount;
    private boolean anonymously;
    private Date gmtCreate;
    private Date gmtModified;

    private PublicationsOfMemberVO author;
}
