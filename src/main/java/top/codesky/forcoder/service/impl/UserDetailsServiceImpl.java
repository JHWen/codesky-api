package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.dao.UserAuthenticationInfoMapper;
import top.codesky.forcoder.model.entity.UserForAuthentication;

/**
 * 查询用户信息服务
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAuthenticationInfoMapper userAuthenticationInfoMapper;

    @Autowired
    public UserDetailsServiceImpl(UserAuthenticationInfoMapper userAuthenticationInfoMapper) {
        this.userAuthenticationInfoMapper = userAuthenticationInfoMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserForAuthentication userForAuthentication = userAuthenticationInfoMapper
                .selectAuthenticationInfoByUsername(username);

        if (userForAuthentication == null) {
            throw new UsernameNotFoundException("username:" + username + "not found");
        }
        return userForAuthentication;
    }
}
