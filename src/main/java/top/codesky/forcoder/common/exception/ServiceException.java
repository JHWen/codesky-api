package top.codesky.forcoder.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @Date: 2019/5/19 15:06
 * @Author: codesky
 * @Description: 自定义业务层异常
 */
public class ServiceException extends BaseException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
