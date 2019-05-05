package top.codesky.forcoder.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.codesky.forcoder.dao.UserMapper;
import top.codesky.forcoder.model.entity.User;
import top.codesky.forcoder.model.entity.UserForAuthentication;
import top.codesky.forcoder.model.other.UserInfo;
import top.codesky.forcoder.model.vo.InfoOfMeVo;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.util.Constants;
import top.codesky.forcoder.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MyAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private final UserMapper userMapper;

    private final static Logger logger = LoggerFactory.getLogger(MyAuthenticationHandler.class);

    @Autowired
    public MyAuthenticationHandler(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //授权成功,将userInfo(username and id)插入session
        HttpSession session = httpServletRequest.getSession();

        // 考虑Principal改为一个字符串，隐藏用户的密码
        UserForAuthentication userForAuthentication = (UserForAuthentication) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo(userForAuthentication.getId(), userForAuthentication.getUsername());
        session.setAttribute(Constants.USER_SESSION_TOKEN, userInfo);

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.OK.value());

        ResponseVo responseVo = new ResponseVo(HttpStatus.OK.value(), "登录成功");

        //登录成功返回个人信息

        //1.查询个人信息
        User user = userMapper.selectByUsername(userForAuthentication.getUsername());

        InfoOfMeVo infoOfMeVo = new InfoOfMeVo();
        infoOfMeVo.setId(user.getId());
        infoOfMeVo.setName(user.getUsername());
        infoOfMeVo.setAvatarUrl(user.getAvatarUrl());
        infoOfMeVo.setGender(user.getGender());

        responseVo.setData(infoOfMeVo);

        logger.debug("userForAuthentication:{}", userForAuthentication);
        logger.debug("infoOfMeVo:{}", infoOfMeVo);


        JsonUtil.getObjectMapper().writeValue(httpServletResponse.getOutputStream(), responseVo);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ResponseVo responseVo = new ResponseVo(601, exception.getMessage());

        JsonUtil.getObjectMapper().writeValue(response.getOutputStream(), responseVo);
    }
}
