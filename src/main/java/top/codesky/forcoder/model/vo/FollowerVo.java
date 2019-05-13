package top.codesky.forcoder.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/5/13 20:25
 * @Author: codesky
 * @Description: 封装返回粉丝列表
 */
public class FollowerVo {
    private List<PublicationsOfMemberVo> followers;

    public FollowerVo() {
        this.followers = new ArrayList<>();
    }

    public List<PublicationsOfMemberVo> getFollowers() {
        return followers;
    }

    public void setFollowers(List<PublicationsOfMemberVo> followers) {
        this.followers.addAll(followers);
    }
}
