package com.zjw.enums;

import lombok.AllArgsConstructor;
/**
 * 返回码规则
 * 0000-0999：错误码
 *  0000-0099：系统错误
 *  0100-0199：权限问题
 *  0200-0299：业务错误/操作错误（前置条件不足）
 *  0300-0399：网络错误（超时等）
 * 1000-2000：正常返回
 *
 */
@AllArgsConstructor
public enum ErrorCode implements RespCode {
    /** "0000","生成验证码失败，请重试" */
    CREAT_CAPTCHA_FAILED("0000","生成验证码失败，请重试"),
    /** 验证码不匹配 */
    CAPTCHA_MISMATCH("0201","验证码不匹配"),

    LOGIN_FAIL("0200","登录失败，请检查用户名或密码");


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
}
