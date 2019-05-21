package top.codesky.forcoder.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2019/5/13 19:34
 * @Author: codesky
 * @Description: 关注结果参数封装, 返回关注者数量
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowVO {
    private long followerCount;
}
