package top.codesky.forcoder.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    // 异常处理返回操作
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        ResponseVo baseResponseVo = new ResponseVo(HttpStatus.UNAUTHORIZED.value(), e.getMessage());

        JsonUtil.getObjectMapper().writeValue(httpServletResponse.getOutputStream(), baseResponseVo);
    }

    // 访问拒绝处理操作
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());

        ResponseVo responseVo = new ResponseVo(HttpStatus.FORBIDDEN.value(), e.getMessage());

        JsonUtil.getObjectMapper().writeValue(httpServletResponse.getOutputStream(), responseVo);
    }
}
