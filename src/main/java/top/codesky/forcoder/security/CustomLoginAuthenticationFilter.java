package top.codesky.forcoder.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录认证拦截器
 */
public class CustomLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomLoginAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest;
        try {
            loginRequest = objectMapper
                    .readValue(request.getReader(), LoginRequest.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException("username or password not provided");
        }
        if (null == loginRequest || StringUtils.isEmpty(loginRequest.getUsername())
                || StringUtils.isEmpty(loginRequest.getPassword())) {
            throw new AuthenticationServiceException("username or password not provided");
        }
        logger.debug("login request:{}", loginRequest);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        this.setDetails(request, authRequest);


        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
