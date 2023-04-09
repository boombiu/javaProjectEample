package com.zjw.constants;


import com.zjw.enums.RespCode;

public class RespResult {
    private String code;
    private String msg;
    private Object data;

    RespResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    RespResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RespResult(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespResult(RespCode respCode, Object data){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
