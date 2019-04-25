package top.codesky.forcoder.model.vo;

/**
 * @Date: 2019/4/24 19:33
 * @Author: codesky
 * @Description: 封装添加回答请求Json数据
 */
public class QuestionAddVo {
    private String title;
    private String content;

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
}
