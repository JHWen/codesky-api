package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codesky.forcoder.common.constant.RegisterResult;
import top.codesky.forcoder.dao.UserAdditionInfoMapper;
import top.codesky.forcoder.dao.UserAuthenticationInfoMapper;
import top.codesky.forcoder.model.entity.UserAdditionInfo;
import top.codesky.forcoder.model.entity.UserAuthenticationInfo;
import top.codesky.forcoder.model.params.UserAdditionInfoUpdateParam;
import top.codesky.forcoder.model.params.UserRegisterParam;
import top.codesky.forcoder.model.vo.PublicationsOfMemberVO;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.BeanUtils;
import top.codesky.forcoder.util.CodeskyUtils;

import java.util.Date;
import java.util.List;

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
     * @param userRegisterParam UserRegisterParam
     * @return RegisterResult 枚举类型，封装注册结果
     */
    @Transactional
    @Override
    public RegisterResult register(UserRegisterParam userRegisterParam) {
        //1.判断两次密码是否一致
        if (!userRegisterParam.getPassword().equals(userRegisterParam.getCheckPassword())) {
            return RegisterResult.USER_ENTERED_PASSWORD_DIFFER;
        }

        //2.查询用户名是否已经注册
        int count = userAuthenticationInfoMapper.selectIfExistByUsername(userRegisterParam.getUsername());
        if (count > 0) {
            return RegisterResult.USER_HAS_EXISTED;
        }

        //3.插入数据到两个表 user_authentication_info 和 user_addition_info
        Date currentDate = new Date();

        UserAuthenticationInfo userAuthenticationInfo = new UserAuthenticationInfo();
        userAuthenticationInfo.setUsername(userRegisterParam.getUsername());
        // 密码+salt哈希
        userAuthenticationInfo.setPassword(passwordEncoder.encode(userRegisterParam.getPassword()));
        userAuthenticationInfo.setAccountNonExpired(true);
        userAuthenticationInfo.setAccountNonLocked(true);
        userAuthenticationInfo.setCredentialsNonExpired(true);
        userAuthenticationInfo.setEnabled(true);
        userAuthenticationInfo.setGmtCreate(currentDate);
        userAuthenticationInfo.setGmtModified(currentDate);

        //1.插入用户认证信息
        userAuthenticationInfoMapper.insert(userAuthenticationInfo);

        // 构造用户介绍描述信息,默认从匿名用户拷贝
        UserAdditionInfo userAdditionInfo = BeanUtils.copyPropertiesFrom(CodeskyUtils.getAnonymousUser(),
                UserAdditionInfo.class);

        // 修改成用户自己的相关属性
        userAdditionInfo.setUsername(userAuthenticationInfo.getUsername());
        userAdditionInfo.setUserId(userAuthenticationInfo.getId());
        userAdditionInfo.setGmtCreate(currentDate);
        userAdditionInfo.setGmtModified(currentDate);

        //2.插入用户描述信息
        userAdditionInfoMapper.insert(userAdditionInfo);

        return RegisterResult.SUCCESS;
    }


    @Override
    public UserAdditionInfo getUserAdditionInfo(Long userId) {
        return userAdditionInfoMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateUserAdditionInfo(UserAdditionInfoUpdateParam params) {
        return userAdditionInfoMapper.updateByUserIdSelective(params) > 0;
    }

    @Override
    public UserAdditionInfo getPublicationsOfMember(Long id) {
        return userAdditionInfoMapper.selectByUserId(id);
    }

    @Override
    public UserAdditionInfo getPublicationsOfMember(String username) {
        return userAdditionInfoMapper.selectByUsername(username);
    }

    @Override
    public List<PublicationsOfMemberVO> getMembersByUserIds(List<Long> ids) {
        return userAdditionInfoMapper.selectMembersByIds(ids);
    }

    @Override
    public boolean userHasExisted(long userId) {
        return userAdditionInfoMapper.countUserByUserId(userId) == 1;
    }
}
