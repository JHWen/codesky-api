package top.codesky.forcoder.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @Date: 2019/5/19 13:53
 * @Author: codesky
 * @Description: BeanUtils Exception
 */
public class BeanUtilsException extends BaseException {

    public BeanUtilsException(String message) {
        super(message);
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
