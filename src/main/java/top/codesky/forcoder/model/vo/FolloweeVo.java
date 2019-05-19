package top.codesky.forcoder.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/5/13 20:32
 * @Author: codesky
 * @Description: 封装关注的人列表
 */
public class FolloweeVo {
    private List<PublicationsOfMemberVO> followees;

    public FolloweeVo() {
        this.followees = new ArrayList<>();
    }

    public List<PublicationsOfMemberVO> getFollowees() {
        return followees;
    }

    public void setFollowees(List<PublicationsOfMemberVO> followees) {
        this.followees.addAll(followees);
    }
}
