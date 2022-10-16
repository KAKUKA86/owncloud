package com.popcorn.owncloud.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
@Service
public interface FileService {
    String uploadMulti(HttpServletRequest request, String filePath);

    void uploadMultiFolder(String filePath, MultipartFile[] files);
}
