package top.codesky.forcoder.service;

import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.model.vo.PublicationsOfMemberVo;
import top.codesky.forcoder.model.vo.ResponseVo;

import java.util.List;

/**
 * @Date: 2019/4/20 12:12
 * @Author: codesky
 * @Description: 用户服务接口
 */
public interface UserService {

    ResponseVo register(String username, String password);

    UserAdditionInfo getUserAdditionInfo(Long userId);

    UserAdditionInfo getPublicationsOfMember(Long id);

    UserAdditionInfo getPublicationsOfMember(String username);

    List<PublicationsOfMemberVo> getMembersByUserIds(List<Long> ids);
}
