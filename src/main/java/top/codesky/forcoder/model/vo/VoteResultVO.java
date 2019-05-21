package top.codesky.forcoder.model.vo;

import lombok.Data;

/**
 * @Date: 2019/5/12 15:06
 * @Author: codesky
 * @Description: 点赞 点踩结果
 */
@Data
public class VoteResultVO {
    private String voting;
    private long voteCount;
}
