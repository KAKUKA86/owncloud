package com.popcorn.owncloud.controller;

import com.popcorn.owncloud.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @Resource
    private FileService service;
    @GetMapping("userHomePage")//get
    public String uploadFile() {
        return "normalUser/userHomePage";
    }

    @PostMapping("/uploadFile")//post
    public String fileUpload(@RequestParam("uploadFile") MultipartFile[] multipartFiles, Model model) {
        com.popcorn.owncloud.bean.File saveFile = new com.popcorn.owncloud.bean.File();
        for (MultipartFile file : multipartFiles) {
            String filename = file.getOriginalFilename();
            //重命名
            filename = UUID.randomUUID() + "_" + filename;
            //指定上传目录
            String path = "D:/test/";
            saveFile.setFileName(filename);
            saveFile.setFileUrl(path);
            saveFile.setFileSize(file.getSize());
            saveFile.setFileTimestamp(System.currentTimeMillis());
            System.out.println(file.getContentType());
            saveFile.setFileType(file.getContentType());
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                service.setNewFile(saveFile);
                file.transferTo(new File(path + filename));
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("status", "上传失败" + e.getMessage());
            }
        }
        model.addAttribute("status", "上传成功");
        return "/normalUser/userHomePage";
    }
    @RequestMapping("/normalUser/userFilePage")
    public String queryFile(Model model) {
        List<com.popcorn.owncloud.bean.File> fileList;
        fileList = service.queryFileList();
        model.addAttribute("fileTables",fileList);
        return "/normalUser/userFilePage";
    }
}


