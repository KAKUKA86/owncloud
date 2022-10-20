package com.popcorn.owncloud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class FileController {
    /**
     * 上传文件
     *
     * @param file 需要上传的文件
     * @return 状态
     */
    @RequestMapping("/uploads")
    @ResponseBody
    public String uploads(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            String fileName = file.getOriginalFilename();
            System.out.println("上传文件名为：" + fileName);
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("文件后缀名为：" + suffixName);

            String filePath = "D:/TestDownLoad/";
            String path = filePath + fileName;
            File dest = new File(path);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @PostMapping("/batch")
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "D:/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())
                    ));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第" + i + " 个文件上传失败 ==>" + e.getMessage();
                }
            }
        }
        return "上传成功";
    }

    @GetMapping("/downloads")
    @ResponseBody
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = " ";//下载所需的文件名
        if (fileName != null) {
            File file = new File("D://test.txt");//路径和文件名
            if (file.exists()) {
                //无法强制打开文件
                response.setContentType("application/force-download");
                //http头信息
                try {
                    response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream = null;
                BufferedInputStream bufferedInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    OutputStream outputStream = response.getOutputStream();
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1) {
                        outputStream.write(buffer, 0, i);
                        i = bufferedInputStream.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedInputStream != null)
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if (fileInputStream != null)
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }

        }
        return "下载失败";
    }

    /**
     * 多文件上传
     *
     * @param multiFile 多个文件
     * @return f/t
     */
    @PostMapping("/multiFleUploads")
    @ResponseBody
    public String upload(@RequestParam("multiFile") MultipartFile[] multiFile) throws IOException {
        if (multiFile.length > 0) {
            for (MultipartFile file : multiFile) {
                // 获取文件原名
                String originalFilename = file.getOriginalFilename();
                // 创建一个新的File对象用于存放上传的文件
                File fileNew = new File("F:\\" + originalFilename);
                file.transferTo(fileNew);
            }
            return "上传完成";
        }
        return "上传失败！";
    }

}
