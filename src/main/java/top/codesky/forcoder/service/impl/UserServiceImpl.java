package top.codesky.forcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.codesky.forcoder.dao.UserMapper;
import top.codesky.forcoder.model.entity.User;
import top.codesky.forcoder.model.entity.UserForAuthentication;
import top.codesky.forcoder.model.other.PublicationsOfMember;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.service.UserService;
import top.codesky.forcoder.util.Constants;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResponseVo register(String username, String password) {
        //1.查询用户名是否已经注册
        UserForAuthentication userSelectFromDatabase = userMapper.findByUsername(username);
        ResponseVo responseVo = new ResponseVo();
        if (userSelectFromDatabase != null) {
            responseVo.setCode(600);
            responseVo.setMsg("用户已存在");
            return responseVo;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatarUrl(Constants.DEFAULT_AVATAR_URl);
        user.setGmtCreate(new Date());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        userMapper.insertSelective(user);

        responseVo.setCode(200);
        responseVo.setMsg("注册成功");

        return responseVo;
    }

    @Override
    public User getUserInfo(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public PublicationsOfMember getPublicationsOfMember(Long id) {
        return userMapper.selectPublicationsOfMemberById(id);
    }

    @Override
    public PublicationsOfMember getPublicationsOfMember(String username) {
        return userMapper.selectPublicationsOfMemberByUsername(username);
    }
}
