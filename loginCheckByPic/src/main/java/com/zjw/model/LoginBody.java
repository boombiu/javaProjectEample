package com.zjw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
public class LoginBody {
    /**
     * 用户名
     */
    @NonNull
    private String userName;

    /**
     * 密码
     */
    @NonNull
    private String userPassword;

    /**
     * 验证码
     */
    @NonNull
    private String code;

    /**
     * 登录标识
     */
    @NonNull
    private String uuid;
    

}
