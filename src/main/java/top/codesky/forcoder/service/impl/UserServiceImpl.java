package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codesky.forcoder.common.constant.ResultCodeEnum;
import top.codesky.forcoder.dao.UserAdditionInfoMapper;
import top.codesky.forcoder.dao.UserAuthenticationInfoMapper;
import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.model.entity.UserAuthenticationInfo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.CodeskyUtils;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {


    private final UserAuthenticationInfoMapper userAuthenticationInfoMapper;

    private final UserAdditionInfoMapper userAdditionInfoMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserAuthenticationInfoMapper userAuthenticationInfoMapper, UserAdditionInfoMapper userAdditionInfoMapper, PasswordEncoder passwordEncoder) {
        this.userAuthenticationInfoMapper = userAuthenticationInfoMapper;
        this.userAdditionInfoMapper = userAdditionInfoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 用户注册业务
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional
    @Override
    public ResponseVo register(String username, String password) {
        //1.查询用户名是否已经注册
        int count = userAuthenticationInfoMapper.selectIfExistByUsername(username);
        if (count > 0) {
            return ResponseVo.error(ResultCodeEnum.USER_HAS_EXISTED);
        }

        //插入两个表 user_authentication_info 和 user_addition_info
        Date currentDate = new Date();

        UserAuthenticationInfo userAuthenticationInfo = new UserAuthenticationInfo();
        userAuthenticationInfo.setUsername(username);
        // 密码+salt哈希
        userAuthenticationInfo.setPassword(passwordEncoder.encode(password));
        userAuthenticationInfo.setAccountNonExpired(true);
        userAuthenticationInfo.setAccountNonLocked(true);
        userAuthenticationInfo.setCredentialsNonExpired(true);
        userAuthenticationInfo.setEnabled(true);
        userAuthenticationInfo.setGmtCreate(currentDate);
        userAuthenticationInfo.setGmtModified(currentDate);

        //1.插入用户认证信息
        userAuthenticationInfoMapper.insert(userAuthenticationInfo);

        // 构造用户介绍描述信息
        UserAdditionInfo userAdditionInfo = UserAdditionInfo.copyOf(CodeskyUtils.getAnonymousUser());

        userAdditionInfo.setUsername(userAuthenticationInfo.getUsername());
        userAdditionInfo.setUserId(userAuthenticationInfo.getId());
        userAdditionInfo.setGmtCreate(currentDate);
        userAdditionInfo.setGmtModified(currentDate);

        //2.插入用户描述信息
        userAdditionInfoMapper.insert(userAdditionInfo);

        return ResponseVo.success(ResultCodeEnum.SUCCESS);
    }


    @Override
    public UserAdditionInfo getUserAdditionInfo(Long userId) {
        return userAdditionInfoMapper.selectByUserId(userId);
    }

    @Override
    public UserAdditionInfo getPublicationsOfMember(Long id) {
        return userAdditionInfoMapper.selectByUserId(id);
    }

    @Override
    public UserAdditionInfo getPublicationsOfMember(String username) {
        return userAdditionInfoMapper.selectByUsername(username);
    }
}
