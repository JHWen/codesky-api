package top.codesky.forcoder.common.constant;

/**
 * @Date: 2019/5/5 11:58
 * @Author: codesky
 * @Description: 自定义服务端返回结果状态码
 */
public enum ResultCodeEnum {

    // 成功 or 失败 状态码
    SUCCESS(0, "success"),
    ERROR(1, "fail"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_REGISTER_ERROR(20006, "用户注册错误"),
    USER_NAME_OR_PASSWORD_IS_EMPTY(20007, "用户名或密码为空"),
    USER_ENTERED_PASSWORD_DIFFER(20008, "两次输入密码不一致"),

    /* 业务错误：30001-39999 */
    SPECIFIED_SERVICE_ERROR(30001, "某业务出现问题"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    RESULE_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 文件上传 */
    UPLOAD_ERROR(80001, "文件上传失败"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    SESSION_TIME_OUT(90001, "Session超时");;

    private final int code;

    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }


    @Override
    public String toString() {
        return this.name();
    }
}
