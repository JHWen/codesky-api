package top.codesky.forcoder.model.params;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Date: 2019/4/24 19:33
 * @Author: codesky
 * @Description: 封装添加回答请求Json数据
 */
@Getter
@Setter
public class QuestionAddParam {
    @NotNull
    @Size(min = 5, message = "问题标题字符长度不能小于5")
    private String title;

    private String content;
}
