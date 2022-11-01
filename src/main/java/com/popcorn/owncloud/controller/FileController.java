package com.popcorn.owncloud.controller;

import com.popcorn.owncloud.bean.CollectFile;
import com.popcorn.owncloud.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    /**
     * 文件下载
     *
     * @param multipartFiles 多文件缓存
     * @param model
     * @return
     */
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

    /**
     * 文件下载方法
     *
     * @param filename 文件名
     * @param fileUrl  文件所在路径
     * @return 下载状态
     */
    @GetMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(String filename, String fileUrl) {
        File file = new File(fileUrl + File.separator + filename);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", filename);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping("/deleteFile")
    public String deleteFile(Integer id, String fileUrl) {
        File file = new File(fileUrl);
        if (file.exists()) {
            file.delete();
            return "/normalUser/userHomePage";
        }
        return "/normalUser/userHomePage";
    }

    @RequestMapping("/collectFile")
    public String collectFile(Integer fileId, Model model) {
        new com.popcorn.owncloud.bean.File();
        com.popcorn.owncloud.bean.File file;
        file = service.queryFileById(fileId);
        service.insertCollectFile(file);
        model.addAttribute("status", "添加成功");
        return "redirect:/normalUser/userFilePage";
    }

    @RequestMapping("/normalUser/userFilePage")
    public String queryFile(Model model) {
        List<com.popcorn.owncloud.bean.File> fileList;
        fileList = service.queryFileList();
        model.addAttribute("fileTables", fileList);
        return "/normalUser/userFilePage";
    }

    @RequestMapping("/normalUser/userCollectFile")
    public String userCollectFile(Model model) {
        List<CollectFile> collectFiles;
        collectFiles = service.queryCollectFileList();
        model.addAttribute("collectTable",collectFiles);
        return "/normalUser/userCollectFile";
    }
}


