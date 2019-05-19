package top.codesky.forcoder.model.vo;

import lombok.Getter;
import lombok.Setter;
import top.codesky.forcoder.common.constant.ItemType;

import java.util.Date;

/**
 * @Date: 2019/5/15 15:27
 * @Author: codesky
 * @Description: 问题+一个回答，用于首页显示
 */
@Getter
@Setter
public class QuestionItemVo {

    private ItemType type;
    private Long id;
    private String title;
    private String content;
    private int answerCount;
    private int commentCount;
    private int followerCount;
    private Date gmtCreate;
    private Date gmtModified;
    private boolean hasFollow;

    private PublicationsOfMemberVO author;

    private AnswerDetailsVo answer;

    public void setQuestion(QuestionWithAuthor question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.answerCount = question.getAnswerCount();
        this.commentCount = question.getCommentCount();
        this.followerCount = question.getFollowerCount();
        this.gmtCreate = question.getGmtCreate();
        this.gmtModified = question.getGmtModified();
        this.author = question.getAuthor();
    }

}
