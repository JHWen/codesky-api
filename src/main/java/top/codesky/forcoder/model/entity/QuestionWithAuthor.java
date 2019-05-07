package top.codesky.forcoder.model.entity;

import top.codesky.forcoder.model.vo.PublicationsOfMemberVo;

import java.util.Date;

/**
 * @Date: 2019/4/25 16:24
 * @Author: codesky
 * @Description: 包含提问者的回答
 */
public class QuestionWithAuthor {
    private Long id;

    private String title;

    private String content;

    private int answerCount;

    private int commentCount;

    private int followerCount;

    private Date gmtCreate;

    private Date gmtModified;


    private PublicationsOfMemberVo author;

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

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public PublicationsOfMemberVo getAuthor() {
        return author;
    }

    public void setAuthor(PublicationsOfMemberVo author) {
        this.author = author;
    }
}
