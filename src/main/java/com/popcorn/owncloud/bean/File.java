package com.popcorn.owncloud.bean;

import org.springframework.stereotype.Component;

@Component
public class File {
    /**
     * 文件ID
     * 文件名
     * 文件上传时间
     * 文件url
     * 文件类型
     * 文件大小
     * 用户ID
     */
    Integer fileId;
    String fileName;
    Integer fileTimestamp;
    String fileUrl;
    String fileType;
    Double fileSize;
    Integer userId;


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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }
}
