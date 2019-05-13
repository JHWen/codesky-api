package top.codesky.forcoder.model.vo;

/**
 * @Date: 2019/5/13 19:34
 * @Author: codesky
 * @Description: 关注结果参数封装, 返回关注者数量
 */
public class FollowVo {
    private long followerCount;

    public FollowVo() {
    }

    public FollowVo(long followerCount) {
        this.followerCount = followerCount;
    }

    public long getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(long followerCount) {
        this.followerCount = followerCount;
    }
}
