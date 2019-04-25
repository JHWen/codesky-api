package top.codesky.forcoder.model.vo;

/**
 * @Date: 2019/4/25 17:51
 * @Author: codesky
 * @Description: 封装问题添加参数 bean
 */
public class AnswerAddVo {

    private Long questionId;
    private String content;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
