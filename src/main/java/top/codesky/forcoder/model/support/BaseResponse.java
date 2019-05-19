package top.codesky.forcoder.model.support;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @Date: 2019/5/18 14:36
 * @Author: codesky
 * @Description: 响应数据实体
 */
@Data
@NoArgsConstructor
public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;

    /**
     * 为了调试方便的异常堆栈信息
     */
    private String devMessage;

    public BaseResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    public static <T> BaseResponse<T> success(@Nullable String message) {
        return new BaseResponse<>(HttpStatus.OK.value(), message, null);
    }

    public static <T> BaseResponse<T> success(@NonNull T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> BaseResponse<T> success(@Nullable String message, @Nullable T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), message, data);
    }

    public static <T> BaseResponse<T> error(HttpStatus httpStatus) {
        return new BaseResponse<>(httpStatus.value(), httpStatus.getReasonPhrase(), null);
    }

    public static <T> BaseResponse<T> error(HttpStatus httpStatus, String message) {
        return new BaseResponse<>(httpStatus.value(), message, null);
    }

}
