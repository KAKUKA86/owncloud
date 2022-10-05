package com.popcorn.owncloud.bean;

import org.springframework.stereotype.Component;

@Component
public class File {
    Integer fileId;
    Integer fileTimestamp;
    String fileUrl;
    Integer userId;
    String fileName;
    String fileOldName;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFileTimestamp() {
        return fileTimestamp;
    }

    public void setFileTimestamp(Integer fileTimestamp) {
        this.fileTimestamp = fileTimestamp;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileOldName() {
        return fileOldName;
    }

    public void setFileOldName(String fileOldName) {
        this.fileOldName = fileOldName;
    }
}
