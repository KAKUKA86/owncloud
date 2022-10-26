package com.popcorn.owncloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    @RequestMapping("normalUser/userUploadFile")
    public String fileUploadPage() {
        return "normalUser/userUploadFile";
    }

    @RequestMapping("userHomePage")
    @ResponseBody
    public String uploadFile(@RequestParam ("filename") MultipartFile file){
        System.out.println("测试进入方法");
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "D:/test";
        File dest = new File(path + "" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);
            return "true";
        }catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

//    @RequestMapping("normalUser/userUploadFile")
//    @ResponseBody
//    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
//        if(file.isEmpty()) {
//            return "false";
//        }
//        String fileName = file.getOriginalFilename();
//        long size = file.getSize();
//        System.out.println(fileName + "-->" + size);
//        String path = "D:/test";
//        File dest = new File(path+"/"+fileName);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdir();
//        }
//        try {
//            file.transferTo(dest);
//            return "true";
//        } catch (IllegalStateException | IOException e) {
//            e.printStackTrace( );
//            return "false";
//        }
}


