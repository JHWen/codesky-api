package top.codesky.forcoder.model.vo;

import top.codesky.forcoder.model.entity.Question;

import java.util.Date;

/**
 * @Date: 2019/4/24 20:07
 * @Author: codesky
 * @Description: 返回给前端的问题的详细信息，question+author
 */
public class QuestionDetailsVo {

    private Long id;
    private String title;
    private String content;
    private int answerCount;
    private Date gmtCreate;
    private Date gmtModified;
    private PublicationsOfMemberVo author;

    public QuestionDetailsVo() {
    }

    public QuestionDetailsVo(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.answerCount = question.getAnswerCount();
        this.gmtCreate = question.getGmtCreate();
        this.gmtModified = question.getGmtModified();
    }

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


}
