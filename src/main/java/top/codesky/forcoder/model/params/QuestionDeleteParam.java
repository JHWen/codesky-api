package top.codesky.forcoder.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2019/4/25 11:00
 * @Author: codesky
 * @Description: 封装删除问题的sql参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDeleteParam {
    private Long questionId;
    private Long authorId;
}
