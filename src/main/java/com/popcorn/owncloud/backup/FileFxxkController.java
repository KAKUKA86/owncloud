//package com.popcorn.owncloud.backup;
//
//import com.popcorn.owncloud.service.FileService;
//import com.popcorn.owncloud.util.FileUtil;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import java.io.*;
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "/file")
//public class FileFxxkController {
//    public String upload(HttpServletRequest request) {
//        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
//        List<MultipartFile> files = params.getFiles("fileFolder");
//        System.out.println("上传文件夹");
//        File file;
//        String fileName = "";
//        String filePath = "";
//        for (MultipartFile f : files) {
//            fileName = f.getOriginalFilename();
//            String type = f.getContentType();
//            System.out.println("\n" + fileName + " ," + type);
//            filePath = "D:\\upload\\" + fileName.substring(0, fileName.lastIndexOf("\\"));
//            if (!isDir(filePath)) {
//                makeDirs(filePath);
//            }
//            file = new File("D:\\upload\\" + fileName);
//            try {
//                file.createNewFile();
//                f.transferTo(file);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return "fileList";
//    }
//
//    public boolean isFile(String filePath) {
//        File f = new File(filePath);
//        return f.exists() && f.isFile();
//    }
//
//    public boolean isDir(String filePath) {
//        File f = new File(filePath);
//        return f.exists() && f.isDirectory();
//    }
//
//    /**
//     * 创建目录
//     *
//     * @param path 目录
//     */
//    public void makeDirs(String path) {
//        File file = new File(path);
//        //如果文件夹不存在则创建文件夹
//        if (!file.exists() && !file.isDirectory()) {
//            file.mkdirs();
//        } else {
//            System.out.println("创建目录失败：" + path);
//        }
//    }
//
//    /**
//     * 上传文件夹
//     *
//     * @param file 文件夹
//     * @param path 路径
//     * @return 状态
//     */
//    @PostMapping("/uploadDir")
//    @ResponseBody
//    public String uploadDir(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
//        if (file.isEmpty()) {
//            return "上传失败，请选择文件";
//        }
//        System.out.println("aaaaaaaaaaaaaaaaaa");
//        String fileName = file.getOriginalFilename();
//        String filePath = path;
//        System.out.println(filePath);
//
//        File dest = new File(filePath + fileName);
//
//        try {
//            file.transferTo(dest);
//            return "上传成功";
//        } catch (IOException ignored) {
//
//        }
//        return "上传失败";
//    }
//
//    /**
//     * 上传多个文件
//     * @param request 请求
//     * @return 状态
//     */
//    @RequestMapping(value = "/uploadMulti", method = RequestMethod.POST)
//    @ResponseBody
//    public Object uploadMulti(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        for (int i = 0; i < files.size(); i++) {
//            file = files.get(i);
//            String filePath = "D:\\upload";
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(filePath + file.getOriginalFilename())
//                    ));
//                    stream.write(bytes);
//                    stream.close();
//                } catch (Exception e) {
//                    stream = null;
//                    e.printStackTrace();
//                    return "the" + i + "file upload failure";
//                }
//            } else {
//                return "the" + i + "file is empty";
//            }
//        }
//        return "upload multiFile success";
//    }
//}
