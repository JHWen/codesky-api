package top.codesky.forcoder.model.vo;

import top.codesky.forcoder.common.constant.ResultEnum;

/**
 * @Date: 2019/4/18 15:43
 * @Author: codesky
 * @Description: 服务端响应的数据封装对象
 */
public class ResponseVo {
    private int code;
    private String msg;

    private Object data;

    public ResponseVo() {
    }

    public ResponseVo(int code, String msg) {
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

    public void setResultCode(ResultEnum resultCode) {
        this.setCode(resultCode.code());
        this.setMsg(resultCode.message());
    }

    //静态工厂方法
    public static ResponseVo success(ResultEnum resultCode) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResultCode(resultCode);
        return responseVo;
    }

    public static ResponseVo success(ResultEnum resultCode, Object data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResultCode(resultCode);
        responseVo.setData(data);
        return responseVo;
    }


    public static ResponseVo error(ResultEnum resultCode) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResultCode(resultCode);
        return responseVo;
    }

    public static ResponseVo error(ResultEnum resultCode, Object data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResultCode(resultCode);
        responseVo.setData(data);
        return responseVo;
    }

}
