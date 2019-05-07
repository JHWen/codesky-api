package top.codesky.forcoder.model.vo;

import java.util.Date;

/**
 * @Date: 2019/5/7 19:19
 * @Author: codesky
 * @Description: 返回给前端的回答的信息
 */
public class AnswerDetailsVo {
    private Long id;
    private String content;
    private String excerpt;
    private int commentCount;
    private int voteupCount;
    private boolean anonymously;
    private Date gmtCreated;
    private Date gmtModified;

    private PublicationsOfMemberVo author;

    public AnswerDetailsVo() {
    }

    public AnswerDetailsVo(Long id, String content, String excerpt, int commentCount, int voteupCount, boolean anonymously, Date gmtCreated, Date gmtModified, PublicationsOfMemberVo author) {
        this.id = id;
        this.content = content;
        this.excerpt = excerpt;
        this.commentCount = commentCount;
        this.voteupCount = voteupCount;
        this.anonymously = anonymously;
        this.gmtCreated = gmtCreated;
        this.gmtModified = gmtModified;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getVoteupCount() {
        return voteupCount;
    }

    public void setVoteupCount(int voteupCount) {
        this.voteupCount = voteupCount;
    }

    public boolean isAnonymously() {
        return anonymously;
    }

    public void setAnonymously(boolean anonymously) {
        this.anonymously = anonymously;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
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
