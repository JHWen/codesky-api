package top.codesky.forcoder.common.constant;

/**
 * @Date: 2019/5/18 19:12
 * @Author: codesky
 * @Description: 注册结果，成功 或 用户名已存在等
 */
public enum RegisterResult {
    SUCCESS("注册成功"),

    USER_HAS_EXISTED("用户已存在"),

    USER_ENTERED_PASSWORD_DIFFER("两次输入的密码不一致"),

    UNKNOW_REASON("未知注册失败原因");

    private String message;

    RegisterResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
