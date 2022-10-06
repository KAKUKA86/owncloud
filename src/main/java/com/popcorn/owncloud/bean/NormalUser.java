package com.popcorn.owncloud.bean;


import org.springframework.stereotype.Component;

@Component
public class NormalUser {
    Integer userID;
    String userPassword;
    String userName;
    Long userPhoneNumber;
    Long userLastLoginTimestamp;
    Long userRegisterTimestamp;
    Long userUpdateTime;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Long userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }
}
