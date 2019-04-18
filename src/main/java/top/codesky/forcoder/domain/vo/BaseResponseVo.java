package top.codesky.forcoder.domain.vo;

/**
 * @Date: 2019/4/18 15:43
 * @Author: codesky
 * @Description: 服务端响应的数据封装对象
 */
public class BaseResponseVo {
    private int code;
    private String msg;

    private Object data;

    public BaseResponseVo() {
    }

    public BaseResponseVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
