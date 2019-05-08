package top.codesky.forcoder.model.entity;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table answer
 */
public class Answer {
    /**
     * Database Column Remarks:
     *   自增主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   回答评论数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.comment_count
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private int commentCount;

    /**
     * Database Column Remarks:
     *   回答点赞数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.voteup_count
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private int voteupCount;

    /**
     * Database Column Remarks:
     *   是否匿名回答
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.is_anonymously
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private boolean anonymously;

    /**
     * Database Column Remarks:
     *   回答者id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.author_id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private Long authorId;

    /**
     * Database Column Remarks:
     *   问题id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.question_id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private Long questionId;

    /**
     * Database Column Remarks:
     *   回答创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.gmt_create
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private Date gmtCreate;

    /**
     * Database Column Remarks:
     *   回答修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.gmt_modified
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private Date gmtModified;

    /**
     * Database Column Remarks:
     *   回答内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.content
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private String content;

    /**
     * Database Column Remarks:
     *   回答内容摘要
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer.excerpt
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    private String excerpt;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Answer(Long id, int commentCount, int voteupCount, boolean anonymously, Long authorId, Long questionId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.commentCount = commentCount;
        this.voteupCount = voteupCount;
        this.anonymously = anonymously;
        this.authorId = authorId;
        this.questionId = questionId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Answer(Long id, int commentCount, int voteupCount, boolean anonymously, Long authorId, Long questionId, Date gmtCreate, Date gmtModified, String content, String excerpt) {
        this.id = id;
        this.commentCount = commentCount;
        this.voteupCount = voteupCount;
        this.anonymously = anonymously;
        this.authorId = authorId;
        this.questionId = questionId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.content = content;
        this.excerpt = excerpt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table answer
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Answer() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.id
     *
     * @return the value of answer.id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.id
     *
     * @param id the value for answer.id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.comment_count
     *
     * @return the value of answer.comment_count
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public int getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.comment_count
     *
     * @param commentCount the value for answer.comment_count
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.voteup_count
     *
     * @return the value of answer.voteup_count
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public int getVoteupCount() {
        return voteupCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.voteup_count
     *
     * @param voteupCount the value for answer.voteup_count
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setVoteupCount(int voteupCount) {
        this.voteupCount = voteupCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.is_anonymously
     *
     * @return the value of answer.is_anonymously
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public boolean isAnonymously() {
        return anonymously;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.is_anonymously
     *
     * @param anonymously the value for answer.is_anonymously
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setAnonymously(boolean anonymously) {
        this.anonymously = anonymously;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.author_id
     *
     * @return the value of answer.author_id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.author_id
     *
     * @param authorId the value for answer.author_id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.question_id
     *
     * @return the value of answer.question_id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.question_id
     *
     * @param questionId the value for answer.question_id
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.gmt_create
     *
     * @return the value of answer.gmt_create
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.gmt_create
     *
     * @param gmtCreate the value for answer.gmt_create
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.gmt_modified
     *
     * @return the value of answer.gmt_modified
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.gmt_modified
     *
     * @param gmtModified the value for answer.gmt_modified
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.content
     *
     * @return the value of answer.content
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.content
     *
     * @param content the value for answer.content
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer.excerpt
     *
     * @return the value of answer.excerpt
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public String getExcerpt() {
        return excerpt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer.excerpt
     *
     * @param excerpt the value for answer.excerpt
     *
     * @mbg.generated 2019-05-05 11:28:05
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt == null ? null : excerpt.trim();
    }
}