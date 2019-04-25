package top.codesky.forcoder.model.entity;

import top.codesky.forcoder.model.other.PublicationsOfMember;

import java.util.Date;

/**
 * @Date: 2019/4/25 16:24
 * @Author: codesky
 * @Description: 包含提问者的回答
 */
public class QuestionWithAuthor {
    private Long id;

    private String title;

    private int answerCount;

    private Date gmtCreate;

    private Date gmtModified;

    private String content;

    private PublicationsOfMember author;

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

    public PublicationsOfMember getAuthor() {
        return author;
    }

    public void setAuthor(PublicationsOfMember author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "QuestionWithAuthor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", answerCount=" + answerCount +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", content='" + content + '\'' +
                ", author=" + author +
                '}';
    }
}
