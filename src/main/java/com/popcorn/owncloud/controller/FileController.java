package com.popcorn.owncloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.UUID;

@Controller
public class FileController {
    @GetMapping("userHomePage")//get
    public String uploadFile() {
        return "normalUser/userHomePage";
    }

    @PostMapping("/uploadFile")//post
    public String fileUpload(@RequestParam("uploadFile") MultipartFile[] multipartFiles, Model model) {
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
        return "normalUser/userHomePage";
    }
}


