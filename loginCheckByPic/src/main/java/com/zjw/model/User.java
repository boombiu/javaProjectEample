package com.zjw.model;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zjw
 * @since 2023-02-21
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private String userEmail;

    private String userPassword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId = " + userId +
            ", userName = " + userName +
            ", userEmail = " + userEmail +
            ", userPassword = " + userPassword +
        "}";
    }
}
