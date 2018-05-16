package com.jinhui.controller.user.vo;

/**
 * Created by jinhui on 2017/9/14.
 */
public class UserVo {
    private String userName;
    private String password;
    private String code;
    private String isWechat;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isWechat() {
        return "1".equals(isWechat);
    }

    public void setIsWechat(String isWechat) {
        this.isWechat = isWechat;
    }
}
