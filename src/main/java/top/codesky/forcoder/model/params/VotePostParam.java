package top.codesky.forcoder.model.params;

import lombok.Data;
import top.codesky.forcoder.common.constant.VoteStatus;

import javax.validation.constraints.NotNull;

/**
 * @Date: 2019/5/12 14:56
 * @Author: codesky
 * @Description: 封装赞踩提交数据：
 * <p>
 * 点赞:type:"up"
 * 点踩：type:"down"
 * 中立：type:"neutral"
 * </p>
 */
@Data
public class VotePostParam {
    @NotNull(message = "参数不能为空")
    private VoteStatus type;
}

