package top.codesky.forcoder.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.Assert;

/**
 * @Date: 2019/5/12 14:36
 * @Author: codesky
 * @Description: 点赞状态：三种 点赞（VoteUp） 中立（neutrality） 点踩（VoteDown）
 */
public enum VoteStatus {
    VOTEUP("up"), NEUTRALITY("neutral"), VOTEDOWN("down");

    private final String value;


    VoteStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public VoteStatus getVoteStatus(String value) {
        Assert.notNull(value, "投票状态字符串必须不为空");
        for (VoteStatus voteStatus : VoteStatus.values()) {
            if (value.equals(voteStatus.getValue())) {
                return voteStatus;
            }
        }
        return null;
    }
}
