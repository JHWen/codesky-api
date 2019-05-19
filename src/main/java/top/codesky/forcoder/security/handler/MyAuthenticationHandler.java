package top.codesky.forcoder.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.codesky.forcoder.common.constant.Base;
import top.codesky.forcoder.model.entity.UserForAuthentication;
import top.codesky.forcoder.model.support.BaseResponse;
import top.codesky.forcoder.model.support.UserInfo;
import top.codesky.forcoder.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MyAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private final static Logger logger = LoggerFactory.getLogger(MyAuthenticationHandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //授权成功,将userInfo(username and id)插入session
        HttpSession session = httpServletRequest.getSession();

        // 考虑Principal改为一个字符串，隐藏用户的密码
        UserForAuthentication userForAuthentication = (UserForAuthentication) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo(userForAuthentication.getId(), userForAuthentication.getUsername());
        session.setAttribute(Base.USER_INFO_SESSION_KEY, userInfo);

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.OK.value());

        logger.debug("userForAuthentication:{}", userForAuthentication);

        JsonUtils.getObjectMapper().writeValue(httpServletResponse.getOutputStream(),
                BaseResponse.success());
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        JsonUtils.getObjectMapper().writeValue(response.getOutputStream(),
                BaseResponse.error(HttpStatus.BAD_REQUEST));
    }
}
