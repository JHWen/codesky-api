package top.codesky.forcoder.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.codesky.forcoder.model.support.BaseResponse;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationExceptionHandler.class);

    /**
     * 未登录，转向登录授权接入点，
     * 一般在前后端不分离的项目中重定向到登录页面，
     * RESTful API接口应用返回未授权错误信息
     *
     * @param httpServletRequest  HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param exception           AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        logger.debug("AuthenticationExceptionHandler in AuthenticationEntryPoint : {}", exception.getMessage());

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        JsonUtils.getObjectMapper().writeValue(httpServletResponse.getOutputStream(),
                BaseResponse.error(HttpStatus.UNAUTHORIZED, exception.getMessage()));
    }

    /**
     * 未登录，访问拒绝处理操作
     *
     * @param httpServletRequest  HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param exception           AccessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException exception) throws IOException, ServletException {
        logger.debug("Access Deny in AccessDeniedHandler caused by : {}", exception.getMessage());

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());

        JsonUtils.getObjectMapper().writeValue(httpServletResponse.getOutputStream(),
                BaseResponse.error(HttpStatus.FORBIDDEN, exception.getMessage()));
    }
}
