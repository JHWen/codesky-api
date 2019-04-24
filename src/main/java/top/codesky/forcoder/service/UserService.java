package top.codesky.forcoder.service;

import top.codesky.forcoder.model.entity.User;
import top.codesky.forcoder.model.other.PublicationsOfMember;
import top.codesky.forcoder.model.vo.ResponseVo;

/**
 * @Date: 2019/4/20 12:12
 * @Author: codesky
 * @Description: 用户服务接口
 */
public interface UserService {
    ResponseVo register(String username, String password);

    User getUserInfo(String username);

    PublicationsOfMember getPublicationsOfMember(Long id);

}
