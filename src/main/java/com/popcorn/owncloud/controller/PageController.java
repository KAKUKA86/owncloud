package com.popcorn.owncloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/toPage")
public class PageController {

    @RequestMapping("userLoginPage/{userHomePage}")
    public String toPage(@PathVariable String userHomePage) {
        String url = "userLoginPage/" + userHomePage;
        return userHomePage;
    }

}
