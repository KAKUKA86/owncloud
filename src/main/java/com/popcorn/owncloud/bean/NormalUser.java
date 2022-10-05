package com.popcorn.owncloud.bean;


import org.springframework.stereotype.Component;

@Component
public class NormalUser {
    Integer userID;
    String userPassword;
    String userName;
    Integer userPhoneNumber;
    Integer userLastLoginTimestamp;
    Integer userRegisterTimestamp;
    Integer userUpdateTime;

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

    public Integer getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(Integer userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Integer getUserLastLoginTimestamp() {
        return userLastLoginTimestamp;
    }

    public void setUserLastLoginTimestamp(Integer userLastLoginTimestamp) {
        this.userLastLoginTimestamp = userLastLoginTimestamp;
    }

    public Integer getUserRegisterTimestamp() {
        return userRegisterTimestamp;
    }

    public void setUserRegisterTimestamp(Integer userRegisterTimestamp) {
        this.userRegisterTimestamp = userRegisterTimestamp;
    }

    public Integer getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Integer userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }
}
