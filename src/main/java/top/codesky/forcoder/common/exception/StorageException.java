package top.codesky.forcoder.common.exception;

/**
 * @Date: 2019/5/10 13:35
 * @Author: codesky
 * @Description: 存储异常
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
