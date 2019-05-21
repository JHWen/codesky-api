package top.codesky.forcoder.model.vo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/5/13 20:32
 * @Author: codesky
 * @Description: 封装关注的人列表
 */
@Getter
public class FolloweeVO {
    private List<PublicationsOfMemberVO> followees;

    public FolloweeVO() {
        this.followees = new ArrayList<>();
    }

    public void setFollowees(List<PublicationsOfMemberVO> followees) {
        this.followees.addAll(followees);
    }
}
