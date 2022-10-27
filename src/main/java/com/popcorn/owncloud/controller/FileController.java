package com.popcorn.owncloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.UUID;

@Controller
public class FileController {
    @RequestMapping("userHomePage")
    public String uploadFile(Model model) {
        return "normalUser/userHomePage";
    }

    @RequestMapping("/uploadFile")
    public String fileUpload(MultipartFile[] multipartFiles, Model model) {
        System.out.println("进入函数");
        for (MultipartFile file : multipartFiles) {
            String filename = file.getOriginalFilename();
            //重命名
            filename = UUID.randomUUID() + "_" + filename;
            //指定上传目录
            String path = "D:/test/";
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                file.transferTo(new File(path + filename));
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("status", "上传失败" + e.getMessage());
            }
        }
        model.addAttribute("status", "上传成功");
        return "/normalUser/userHomePage";
    }
}


