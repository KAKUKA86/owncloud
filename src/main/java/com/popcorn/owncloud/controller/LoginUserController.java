package com.popcorn.owncloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.popcorn.owncloud.bean.Administrator;
import com.popcorn.owncloud.bean.NormalUser;
import com.popcorn.owncloud.service.AdministratorService;
import com.popcorn.owncloud.service.NormalUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

/**
 * @author 14717
 * 后期数据库可以存储BASE64加密后的密码（安全性问题）
 */

@Controller
public class LoginUserController {
    @Resource
    private NormalUserService normalUserService;

    @Resource
    private AdministratorService administratorService;

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录验证模块(写入时间戳功能暂未实现)
     */

    @PostMapping("/userLogin")
    @ResponseBody
    public String userLogin(HttpServletRequest request) {

        JSONObject result = new JSONObject();
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        NormalUser normalUser = normalUserService.queryNormalUsersByUserName(userName);

        if (normalUser == null) {
            System.out.println("失败1号状态");
            result.put("state", "error");
            return result.toJSONString();
        }

        if (!Objects.equals(userPassword, normalUser.getUserPassword())) {
            System.out.println("失败2号状态");
            result.put("state", "error");
            return result.toJSONString();
        }

        normalUser.setUserLastLoginTimestamp(System.currentTimeMillis());
        //这里的对象属性已经有name和password
        //仅添加无返回值
        normalUserService.addedLogonTimestamp(normalUser);
        result.put("state", "success");
        return result.toJSONString();
    }


    /**
     * 普通用户注册模块
     */
    @PostMapping("/userSignIn")
    @ResponseBody
    public String userSignIn(HttpServletRequest request) {
        NormalUser newUser = new NormalUser();
        newUser.setUserName(request.getParameter("userName"));
        newUser.setUserPassword(request.getParameter("userPassword"));
        newUser.setUserPhoneNumber(Long.valueOf(request.getParameter("userPhoneNumber")));
        newUser.setUserRegisterTimestamp(System.currentTimeMillis());
        Integer status = normalUserService.userSigIn(newUser);
        JSONObject result = new JSONObject();
        if (status > 0) {
            result.put("state", "success");
            return result.toJSONString();
        }
        result.put("state", "error");
        return result.toJSONString();
    }


    /**
     * 管理员验证模块
     */
    @PostMapping("/admin/adminLogin")
    @ResponseBody
    public String adminLogin(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String adminName = request.getParameter("adminName");
        String adminPassword = request.getParameter("adminPassword");
        Administrator administrator = administratorService.queryAdministratorsByAdminName(adminName);
        System.out.println(adminName);
        System.out.println(adminPassword);
        if (administrator == null) {
            System.out.println("错误一");
            result.put("state", "error");
            return result.toJSONString();
        }
        if (!Objects.equals(adminPassword, administrator.getAdminPassword())){
            System.out.println("错误二");
            result.put("state","error");
            return result.toJSONString();
        }
        result.put("state", "success");
        return result.toJSONString();
    }

}
