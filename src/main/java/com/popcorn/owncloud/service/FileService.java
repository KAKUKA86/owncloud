package com.popcorn.owncloud.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Objects;

@Service
public interface FileService {
    Object multiUploadFiles(HttpServletRequest request,String path);
    static boolean isFile(String filepath){
        File f = new File(filepath);
        return f.exists() && f.isFile();
    }
    static boolean isDir(String dirPath) {
        File f = new File(dirPath);
        return f.exists() && f.isDirectory();
    }
    void deleteFile(String filePath);
    boolean deleteAllByPath(File rootFilePath);
    boolean deleteFile(File rootFilePath);
    String fixFileName (String filePath, String newFileName);
    List<String> getAllDirection(String filePath);
    String getDirection();
    List<String> getAllDirs(String directoryPath, boolean isAddDirectory);
    List<Object> getAllFiles(String directoryPath);
    String getFileTime(String filePath);
    String getFileSize(String path);
    String formatFileSize(long fileLength);
    String uploadMultiFile(HttpServletRequest request,String filePath);
    void uploadMultiFolder(String filePath, MultipartFile[] files);
}
