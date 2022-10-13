package com.popcorn.owncloud.bean;


import org.springframework.stereotype.Component;


@Component
public class NormalUser {
    /**
     * 用户ID
     * 用户名
     * 用户密码
     * 用户存储空间
     * 用户电话
     * 用户最后登陆时间
     * 用户注册时间
     */
    Integer userID;
    String userName;
    String userPassword;
    Double userStorage;
    Long userPhoneNumber;
    Long userLastLoginTimestamp;
    Long userRegisterTimestamp;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Double getUserStorage() {
        return userStorage;
    }

    public void setUserStorage(Double userStorage) {
        this.userStorage = userStorage;
    }

    public Long getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(Long userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Long getUserLastLoginTimestamp() {
        return userLastLoginTimestamp;
    }

    public void setUserLastLoginTimestamp(Long userLastLoginTimestamp) {
        this.userLastLoginTimestamp = userLastLoginTimestamp;
    }

    public Long getUserRegisterTimestamp() {
        return userRegisterTimestamp;
    }

    public void setUserRegisterTimestamp(Long userRegisterTimestamp) {
        this.userRegisterTimestamp = userRegisterTimestamp;
    }
}
