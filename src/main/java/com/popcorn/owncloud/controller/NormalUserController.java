package com.popcorn.owncloud.controller;

import com.popcorn.owncloud.service.NormalUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.annotation.Resource;


@Controller
public class NormalUserController {
    @Resource
    private NormalUserService service;

    @GetMapping("/deleteNormalUser")
    public String deleteNormalUser(int id) {
        service.deleteUserById(id);
        return "redirect:/admin/user-list";
    }


}
