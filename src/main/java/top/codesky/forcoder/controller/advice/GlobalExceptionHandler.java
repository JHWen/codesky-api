package top.codesky.forcoder.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.codesky.forcoder.model.support.BaseResponse;
import top.codesky.forcoder.util.CodeskyUtils;

/**
 * @Date: 2019/5/18 15:15
 * @Author: codesky
 * @Description: controlller层面的全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BaseResponse baseResponse = handleBaseException(exception);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        baseResponse.setStatus(httpStatus.value());
        baseResponse.setMessage("字段校验错误，请仔细检查参数！");

        //todo: 读取字段具体校验错误信息返回给前端

        return baseResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        BaseResponse baseResponse = handleBaseException(e);
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        baseResponse.setMessage("Required request body is missing");
        return baseResponse;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleGloabalException(Exception e) {
        BaseResponse baseResponse = handleBaseException(e);

        baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        baseResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return baseResponse;
    }

    private <T> BaseResponse<T> handleBaseException(Throwable t) {
        Assert.notNull(t, "throw must not null!");

        log.error("capture a exception");

        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(t.getMessage());
        if (log.isDebugEnabled()) {
            baseResponse.setDevMessage(CodeskyUtils.getStackTrace(t));
        }
        return baseResponse;
    }
}
