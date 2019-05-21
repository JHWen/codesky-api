package top.codesky.forcoder.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2019/5/20 19:54
 * @Author: codesky
 * @Description: 通过用户对某问题的回答数，判断是否已经回答
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCountQuery {
    private long questionId;
    private long authorId;
}
