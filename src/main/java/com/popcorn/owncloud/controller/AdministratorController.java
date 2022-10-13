package com.popcorn.owncloud.controller;



import com.alibaba.fastjson.JSONObject;
import com.popcorn.owncloud.bean.Administrator;
import com.popcorn.owncloud.service.AdministratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class AdministratorController {

    @RequestMapping("/admin/login")
    public String adminLogin() {
        return "/admin/login";
    }
    @Resource
    private AdministratorService service;

    /**
     * 管理员普通用户查询
     * @return
     */
    @PostMapping("/queryNormalUser")
    public String queryNormalUser() {
        List<Administrator> administratorList;
        JSONObject result = new JSONObject();
        administratorList = service.queryNormalUser();
        result.put("rows", administratorList);
        return result.toJSONString();
    }

}
