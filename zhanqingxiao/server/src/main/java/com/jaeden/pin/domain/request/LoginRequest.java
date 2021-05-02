package com.jaeden.pin.domain.request;

import com.jaeden.pin.domain.user.UserInfo;

/**
 * 类LoginInfo.java的实现描述：TODO
 *
 */
public class LoginRequest {
    private String code;
    private UserInfo userInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "code='" + code + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
