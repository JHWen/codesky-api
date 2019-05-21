package top.codesky.forcoder.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @Date: 2019/5/10 13:35
 * @Author: codesky
 * @Description: 文件存储异常
 */
public class StorageException extends BaseException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
