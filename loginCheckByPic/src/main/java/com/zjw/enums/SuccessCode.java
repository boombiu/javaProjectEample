package com.zjw.enums;

/**
 * 返回码规则
 * 0000-0999：错误码
 ** 0000-0099：系统错误
 ** 0100-0199：权限问题
 ** 0200-0299：业务错误/操作错误（前置条件不足）
 ** 0300-0399：网络错误（超时等）
 * 1000-2000：正常返回
 ** 1000-1099: 操作成功
 */
public enum SuccessCode implements RespCode{

    LOGIN_SUCCESS("1001","登录成功"),

    CREAT_CAPTCHA_SUCCESS("1002","生成验证码成功")
    ;

    private String code;
    private String msg;
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    SuccessCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
