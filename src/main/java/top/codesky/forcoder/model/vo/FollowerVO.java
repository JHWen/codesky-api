package top.codesky.forcoder.model.vo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/5/13 20:25
 * @Author: codesky
 * @Description: 封装返回粉丝列表
 */
@Getter
public class FollowerVO {
    private List<PublicationsOfMemberVO> followers;

    public FollowerVO() {
        this.followers = new ArrayList<>();
    }

    public void setFollowers(List<PublicationsOfMemberVO> followers) {
        this.followers.addAll(followers);
    }
}
