package com.popcorn.owncloud.controller;

import ch.qos.logback.core.util.FileUtil;
import com.popcorn.owncloud.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class FileController {
    @Resource
    private FileService service;

    /**
     * 多文件上传
     *
     * @param request  请求信息
     * @param filePath 上传文件路径
     * @return dataMap
     */
    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
    public Object uploadMultiFile(HttpServletRequest request, String filePath) {
        Map<String, Object> dataMap = new HashMap<>();
        try {
            String str = service.uploadMultiFile(request, filePath);
            dataMap.put("data", str);
            dataMap.put("code", 200);
            dataMap.put("msg", "多文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            dataMap.put("data", "");
            dataMap.put("code", 500);
            dataMap.put("msg", "多文件上传失败");
        }
        return dataMap;
    }

    /**
     * @param files    待上传文件的文件夹
     * @param filePath 需要上传的目录路径
     * @return dataMap
     */
    @RequestMapping(value = "/uploadFolder", method = RequestMethod.POST)
    public Object uploadFolder(MultipartFile[] files, String filePath) {
        Map<String, Object> dataMap = new HashMap<>();
//       try {
        service.uploadMultiFolder(filePath, files);
        dataMap.put("data", "");
        dataMap.put("code", 200);
        dataMap.put("msg", "文件上传成功");
//       } catch (ExportException e) {
//           dataMap.put("data" , "");
//           dataMap.put("code" , 500);
//           dataMap.put("msg" , "文件上传失败");
//           e.printStackTrace();
//       }
        return dataMap;
    }

    @RequestMapping("/download")
    public String download(HttpServletResponse response, String filePath) throws UnsupportedEncodingException {
        File file = new File(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition","attachment;filename=" + fileName + ";filename*=utf-8''"
            + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        }
        return null;
    }
}
