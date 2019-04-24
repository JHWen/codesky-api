package top.codesky.forcoder.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.codesky.forcoder.model.entity.UserForAuthentication;
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
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //授权成功,将username插入session
        HttpSession session = httpServletRequest.getSession();
        UserForAuthentication userForAuthentication = (UserForAuthentication) authentication.getPrincipal();
        session.setAttribute(Constants.USER_SESSION_TOKEN, userForAuthentication.getUsername());

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.OK.value());

        ResponseVo responseVo = new ResponseVo(HttpStatus.OK.value(), "login success");

        JsonUtil.getObjectMapper().writeValue(httpServletResponse.getOutputStream(), responseVo);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ResponseVo responseVo = new ResponseVo(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());

        JsonUtil.getObjectMapper().writeValue(response.getOutputStream(), responseVo);
    }
}
