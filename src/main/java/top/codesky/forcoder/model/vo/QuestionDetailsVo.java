package top.codesky.forcoder.model.vo;

import top.codesky.forcoder.model.entity.Question;

import java.util.Date;
import java.util.List;

/**
 * @Date: 2019/4/24 20:07
 * @Author: codesky
 * @Description: 返回给前端的问题的详细信息，question+author+answer
 */
public class QuestionDetailsVo {

    private Long id;
    private String title;
    private String content;
    private int answerCount;
    private int commentCount;
    private int followerCount;
    private Date gmtCreate;
    private Date gmtModified;

    //提问者信息
    private PublicationsOfMemberVo author;

    //回答信息
    private List<AnswerDetailsVo> answers;


    public QuestionDetailsVo() {
    }

    public QuestionDetailsVo(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.answerCount = question.getAnswerCount();
        this.commentCount = question.getCommentCount();
        this.followerCount = question.getFollowerCount();
        this.gmtCreate = question.getGmtCreate();
        this.gmtModified = question.getGmtModified();
    }

    public QuestionDetailsVo(Question question, PublicationsOfMemberVo author, List<AnswerDetailsVo> answers) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.answerCount = question.getAnswerCount();
        this.commentCount = question.getCommentCount();
        this.followerCount = question.getFollowerCount();
        this.gmtCreate = question.getGmtCreate();
        this.gmtModified = question.getGmtModified();

        this.author = author;
        this.answers = answers;
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

    public List<AnswerDetailsVo> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDetailsVo> answers) {
        this.answers = answers;
    }
}
