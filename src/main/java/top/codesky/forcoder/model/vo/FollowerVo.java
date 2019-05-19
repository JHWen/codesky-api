package top.codesky.forcoder.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/5/13 20:25
 * @Author: codesky
 * @Description: 封装返回粉丝列表
 */
public class FollowerVo {
    private List<PublicationsOfMemberVO> followers;

    public FollowerVo() {
        this.followers = new ArrayList<>();
    }

    public List<PublicationsOfMemberVO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<PublicationsOfMemberVO> followers) {
        this.followers.addAll(followers);
    }
}
