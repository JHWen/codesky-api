package top.codesky.forcoder.common.constant;

/**
 * @Date: 2019/5/20 19:16
 * @Author: codesky
 * @Description: 操作处理结果
 */
public enum ProcessStatusEnum {
    SUCCESS("成功"),

    FAIL("失败"),

    UNKNOWN_REASON("未知结果"),

    RECORD_NOT_EXIST("记录不存在"),

    RECORD_HAS_EXISTED("记录已存在");

    private final String message;

    ProcessStatusEnum(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
