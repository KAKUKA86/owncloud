package com.popcorn.owncloud.service;

import com.popcorn.owncloud.bean.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Service
public interface FileService {
//    Object multiUploadFiles(HttpServletRequest request, String path);
//
//    static boolean isFile(String filepath) {
//        File f = new File(filepath);
//        return f.exists() && f.isFile();
//    }
//
//    static boolean isDir(String dirPath) {
//        File f = new File(dirPath);
//        return f.exists() && f.isDirectory();
//    }
//
//    static boolean makeDirs(String path) {
//        File file = new File(path);
//        boolean flag = false;
//        try {
//            if (!file.exists() && !file.isDirectory()) {
//                file.mkdirs();
//                flag = true;
//            } else {
//                int i = 1;
//                String name = path.substring(path.lastIndexOf("/") + 1, path.length());
//                while (file.exists()) {
//                    String newFilename = name + "(" + i + ")";
//                    String parentPath = file.getParent();
//                    file = new File(parentPath + File.separator+newFilename);
//                    i++;
//                }
//                file.mkdirs();
//                flag = true;
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//    void deleteFile(String filePath);
//
//    boolean deleteAllByPath(File rootFilePath);

//    boolean deleteFile(File rootFilePath);
//
//    String fixFileName(String filePath, String newFileName);
//
//    List<String> getAllDirection(String filePath);
//
//    String getDirection();
//
//    List<String> getAllDirs(String directoryPath, boolean isAddDirectory);
//
//    List<Object> getAllFiles(String directoryPath);

//    String getFileTime(String filePath);
//
//    String getFileSize(String path);
//
//    String formatFileSize(long fileLength);

//    String uploadMultiFile(HttpServletRequest request, String filePath);

//    void uploadMultiFolder(String filePath, MultipartFile[] files);

    void setNewFile(com.popcorn.owncloud.bean.File saveFile);
    List<File> queryFileList();


}
