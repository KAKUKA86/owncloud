package com.popcorn.owncloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.tools.FileObject;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FileController {
    @Value("$file.base.director")
    private String fileBaseDirector;
    private Path fileBasePath;

    @Autowired
    private void createDirectories(){
        try {
            Files.createDirectories(Paths.get(fileBaseDirector));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fileBasePath = Path.of(fileBaseDirector);
    }

    @GetMapping("/files")
    public String files(Model model) throws IOException {
        List<FileObject> fileObjects = Files.walk(fileBasePath,1)
                .filter(path -> !path.equals(fileBasePath))
                .map(path -> {
                    String url = MvcUriComponentsBuilder.fromMethodName(FileController
                            .class,"loadFile",
                            path.getFileName().toString()).build().toString();
                    FileObject fileObject = new FileObject(path.getFileName().toString(),url);
                })
        return null;
    }
}
