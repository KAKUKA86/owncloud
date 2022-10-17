package com.popcorn.owncloud.service.impl;

import com.popcorn.owncloud.action.FileMapper;
import com.popcorn.owncloud.service.FileService;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper mapper;

    @Value("${fileManager.dirPath}")
    private String dirPath;

    /**
     * @param request 请求
     * @param path    上传的目录路径
     * @return
     */
    @Override
    public Object multiUploadFiles(HttpServletRequest request, String path) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = path;

        int failCount = 0;
        int successCount = 0;

        Map<String, Object> uploadMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                failCount++;
                return "上传第" + (i++) + "个文件失败";
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);

            try {
                file.transferTo(dest);
                successCount++;
            } catch (IOException e) {
                failCount++;
                e.printStackTrace();
                ;
                return "上传第" + (i++) + "个文件失败";
            }
        }
        uploadMap.put("failNumber", failCount);
        uploadMap.put("successNumber", successCount);
        return uploadMap;
    }

    /**
     * 创建多级目录
     *
     * @param path 路径
     * @return 是否成功创建
     */
    private static boolean makeDir(String path) {
        File file = new File(path);
        boolean flag = false;
        try {
            if (file.exists() || file.isDirectory()) {
                int i = 1;
                String name = path.substring(path.lastIndexOf("/") + 1, path.length());
                while (file.exists()) {
                    String newFilename = name + "(" + i + ")";
                    String parentPath = file.getParent();
                    file = new File(parentPath + File.separator + newFilename);
                    i++;
                }
            }
            file.mkdirs();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除路径
     *
     * @param filePath 文件路径
     */
    @Override
    public void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除某个文件下所有文件及文件夹
     * @param rootFilePath 根目录
     * @return boolean
     */
    @Override
    public boolean deleteAllByPath(File rootFilePath) {
        File[] deleteFiles = rootFilePath.listFiles();
        if (deleteFiles == null) {
            return true;
        }
        for (int i = 0; i < deleteFiles.length; i++) {
            if (deleteFiles[i].isDirectory()) {
                deleteAllByPath(deleteFiles[i]);
            }
            try {
                Files.delete(deleteFiles[i].toPath());
            } catch (IOException e) {
                System.out.println("Delete temp directory for failed");
                return false;
            }
        }
        return true;
    }

    /**
     * @param rootFilePath 根目录
     * @return Boolean
     */
    @Override
    public boolean deleteFile(File rootFilePath) {
        if (rootFilePath.isDirectory()) {
            for (File file : rootFilePath.listFiles()) {
                deleteFile(file);
            }
        }
        return rootFilePath.delete();
    }

    /**
     * 修改文件名
     *
     * @param filePath 文件路径
     * @param newFileName 新的文件名
     * @return 新的文件名
     */
    @Override
    public String fixFileName(String filePath, String newFileName) {
        File f = new File(filePath);
        if (!f.exists()) {
            return null;
        }
        newFileName = newFileName.trim();
        if ("".equals(newFileName) || newFileName == null)
            return null;
        String newFilePath = null;
        if (f.isDirectory()) {
            int i = filePath.lastIndexOf("\\");
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName
                    + filePath.substring(filePath.lastIndexOf("."));
        }
        File nf = new File(newFilePath);
        try {
            f.renameTo(nf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return newFilePath;
    }

    /**
     * 上传多个文件
     * @param basePath 文件路径
     * @param files    文件
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
            String filename = "";
//            String filename = file.getOriginalFilename();
//            String filePath = basePath + "/" + file.getOriginalFilename();
            CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
            FileItem fileItem = commonsMultipartFile.getFileItem();
            String fileStr = fileItem.toString();
            String[] split = fileStr.split(",");
            for (String s : split) {
                if (s.contains("name=")) {
                    filename = s.substring(5, split.length);
                }
            }
            String filePath = basePath + "/" + filename;
            makeDir(filePath);
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getAllDirection(String filePath) {
        if (filePath == null || "".equals(filePath)) {
            filePath = dirPath;
        }
        List<String> dirList = getAllDirs(filePath, true);
        return dirList;
    }

    /**
     * 获取原始目录
     * @return 目录
     */
    @Override
    public String getDirection() {
        return dirPath;
    }

    @Override
    public List<String> getAllDirs(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File (directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory){
                   list.add(file.getAbsolutePath());
                }
                list.addAll(getAllDirs(file.getAbsolutePath(),isAddDirectory));
            }
        }
        return list;
    }

    /**
     * 获取路径下的所有文件
     * @param directoryPath 文件夹路径
     * @return
     */

    @Override
    public List<Object> getAllFiles(String directoryPath) {
        List <Object> list = new ArrayList<Object>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            Map<String, String> map = new HashMap<>();
            if (file.isFile()) {
                map.put("fileName",file.getName());
                map.put("filePath", file.getAbsolutePath());
                map.put("time",getFileTime(file.getAbsolutePath()));
                map.put("size",getFileSize(file.getAbsolutePath()));
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 获取文件最后修改时间
     * @param filePath 文件目录
     * @return
     */
    @Override
    public String getFileTime(String filePath) {
        File file = new File(filePath);
        long time = file.lastModified();//返回文件最后修改时间，以long的毫秒数
        String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
        return ctime;
    }

    /**
     * 获取文件大小
     * @param path
     * @return
     */
    @Override
    public String getFileSize(String path) {
        File file = new File(path);
        long length = file.length();
        return formatFileSize(length);
    }

    /**
     * 转换文件大小
     * @param fileLength 文件大小
     * @return
     */
    @Override
    public String formatFileSize(long fileLength) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        DecimalFormat decimal = new DecimalFormat("#");
        String fileSizeString = "";
        if (fileLength < 1024) {
            fileSizeString = decimal.format((double) fileLength)+"B";
        } else if (fileLength <1048576) {
            fileSizeString = decimalFormat.format((double) fileLength / 1024)+"KB";
        } else if (fileLength < 1073741824) {
            fileSizeString = decimalFormat.format((double) fileLength/1048576) +"MB";
        }else {
            fileSizeString = decimalFormat.format((double) fileLength/1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 多文件上传 服务层
     *
     * @param request 请求
     * @param filePath 文件路径
     * @return String 状态
     */
    @Override
    public String uploadMultiFile(HttpServletRequest request, String filePath) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        StringBuilder filePathBuilder = new StringBuilder(filePath);
        for (int i = 0; i < files.size(); i++) {
            file = files.get(i);
            filePathBuilder.append("/");
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePathBuilder + file.getOriginalFilename())
                    ));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    e.printStackTrace();
                    return i + " 个文件上传失败";
                }
            } else {
                return i + " 个文件是空的";
            }
        }
        filePath = filePathBuilder.toString();
        return "多个文件上传成功";
    }

}
