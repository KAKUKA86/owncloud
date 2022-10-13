package com.popcorn.owncloud.bean;

import org.springframework.stereotype.Component;

@Component
public class Administrator {
    /**
     * 管理员ID
     * 管理员等级
     * 管理员名
     * 管理员密码
     * 管理员电话
     */
    Integer adminId;
    Integer adminLevel;
    String adminName;
    String adminPassword;
    Long adminNumber;

    public Integer getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(Integer adminLevel) {
        this.adminLevel = adminLevel;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Long getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(Long adminNumber) {
        this.adminNumber = adminNumber;
    }
}
