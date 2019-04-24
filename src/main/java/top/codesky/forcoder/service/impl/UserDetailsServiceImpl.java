package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.dao.UserMapper;
import top.codesky.forcoder.model.entity.UserForAuthentication;

/**
 * 查询用户信息服务
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserForAuthentication user = userMapper.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("username:" + username + "not found");
        }
        return user;
    }
}
