package com.popcorn.owncloud.controller;

import com.popcorn.owncloud.bean.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {
    @RequestMapping("/uploadFiles")
    public String upLoadFiles (File file){
        return null;
    }
}
