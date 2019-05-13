package top.codesky.forcoder.model.vo;

/**
 * @Date: 2019/5/12 15:06
 * @Author: codesky
 * @Description: 点赞 点踩结果
 */
public class VoteResult {
    private String voting;
    private long voteCount;

    public VoteResult() {
    }

    public VoteResult(String voting, long voteCount) {
        this.voting = voting;
        this.voteCount = voteCount;
    }

    public String getVoting() {
        return voting;
    }

    public void setVoting(String voting) {
        this.voting = voting;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }
}
