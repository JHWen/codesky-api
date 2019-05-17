package top.codesky.forcoder.model.vo;

import top.codesky.forcoder.common.constant.ItemType;
import top.codesky.forcoder.model.entity.QuestionWithAuthor;

import java.util.Date;

/**
 * @Date: 2019/5/15 15:27
 * @Author: codesky
 * @Description: 问题+一个回答，用于首页显示
 */
public class QuestionItemVo {
    private Long id;

    private String title;

    private String content;

    private int answerCount;

    private int commentCount;

    private int followerCount;

    private Date gmtCreate;

    private Date gmtModified;

    private ItemType type;

    private PublicationsOfMemberVo author;

    private AnswerDetailsVo answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public PublicationsOfMemberVo getAuthor() {
        return author;
    }

    public void setAuthor(PublicationsOfMemberVo author) {
        this.author = author;
    }

    public AnswerDetailsVo getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerDetailsVo answer) {
        this.answer = answer;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

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
