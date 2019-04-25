package top.codesky.forcoder.model.query;

/**
 * @Date: 2019/4/25 11:00
 * @Author: codesky
 * @Description: 封装删除问题的sql参数
 */
public class QuestionDeleteParams {
    private Long questionId;
    private Long authorId;

    public QuestionDeleteParams() {
    }

    public QuestionDeleteParams(Long questionId, Long authorId) {
        this.questionId = questionId;
        this.authorId = authorId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
