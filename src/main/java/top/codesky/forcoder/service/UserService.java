package top.codesky.forcoder.service;

import top.codesky.forcoder.common.constant.RegisterResult;
import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.model.params.UserAdditionInfoUpdateParam;
import top.codesky.forcoder.model.params.UserRegisterParam;
import top.codesky.forcoder.model.vo.PublicationsOfMemberVO;

import java.util.List;

/**
 * @Date: 2019/4/20 12:12
 * @Author: codesky
 * @Description: 用户服务接口
 */
public interface UserService {

    RegisterResult register(UserRegisterParam userRegisterParam);

    UserAdditionInfo getUserAdditionInfo(Long userId);

    boolean updateUserAdditionInfo(UserAdditionInfoUpdateParam params);

    UserAdditionInfo getPublicationsOfMember(Long id);

    UserAdditionInfo getPublicationsOfMember(String username);

    List<PublicationsOfMemberVO> getMembersByUserIds(List<Long> ids);
}
