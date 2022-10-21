package com.popcorn.owncloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("normalUser/userHomePage")
    public String register() {
        return "normalUser/userHomePage";
    }
}
