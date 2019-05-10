package top.codesky.forcoder.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import top.codesky.forcoder.common.constant.ResultCodeEnum;
import top.codesky.forcoder.model.vo.ResponseVo;
import top.codesky.forcoder.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date: 2019/5/2 10:56
 * @Author: codesky
 * @Description: forcoder
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.OK.value());

        JsonUtils.getObjectMapper().writeValue(response.getOutputStream(),
                ResponseVo.success(ResultCodeEnum.SUCCESS));
    }
}
