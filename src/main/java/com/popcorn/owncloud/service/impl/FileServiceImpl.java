package com.popcorn.owncloud.service.impl;

import com.popcorn.owncloud.action.FileMapper;
import com.popcorn.owncloud.service.FileService;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper mapper;

    /**
     * 多文件上传 服务层
     * @param request
     * @param filePath
     * @return
     */
    @Override
    public String uploadMulti(HttpServletRequest request, String filePath) {
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        StringBuilder filePathBuilder = new StringBuilder(filePath);
        for (int i = 0; i < files.size(); i++) {
            file = files.get(i);
            filePathBuilder.append("/");
            if(!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePathBuilder + file.getOriginalFilename())
                    ));
                    stream.write(bytes);
                    stream.close();
                }catch (Exception e){
                    stream = null;
                    e.printStackTrace();
                    return i+" 个文件上传失败";
                }
            }else {
                return i + " 个文件是空的";
            }
        }
        filePath = filePathBuilder.toString();
        return "多个文件上传成功";
    }

    /**
     *
     * @param filePath 文件路径
     * @param files 文件
     */
    @Override
    public void uploadMultiFolder(String basePath, MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return;
        }
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String filePath = basePath + "/" + file.getOriginalFilename();
            CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
            FileItem fileItem = commonsMultipartFile.getFileItem();



        }
    }
}
