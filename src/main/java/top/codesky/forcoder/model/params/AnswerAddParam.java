package top.codesky.forcoder.model.params;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @Date: 2019/4/25 17:51
 * @Author: codesky
 * @Description: 封装问题添加参数
 */
@Data
public class AnswerAddParam {
    @NotNull(message = "问题id不能为空")
    @Positive(message = "问题id必须为正数")
    private long questionId;

    @NotEmpty(message = "答案内容不能为空")
    private String content;
}
