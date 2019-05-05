package top.codesky.forcoder.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import top.codesky.forcoder.model.vo.LoginRequestVo;
import top.codesky.forcoder.util.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录认证拦截器
 */
public class CustomLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomLoginAuthenticationFilter.class);
    private boolean postOnly = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 仅支持POST方式的登录请求
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        LoginRequestVo loginRequestVo;
        try {
            loginRequestVo = JsonUtils.getObjectMapper()
                    .readValue(request.getReader(), LoginRequestVo.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException("username or password not provided");
        }
        if (loginRequestVo == null || StringUtils.isEmpty(loginRequestVo.getUsername())
                || StringUtils.isEmpty(loginRequestVo.getPassword())) {
            throw new AuthenticationServiceException("username or password not provided");
        }
        logger.debug("filter login request:{}", loginRequestVo);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequestVo.getUsername(), loginRequestVo.getPassword());

        this.setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
