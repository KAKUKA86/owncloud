package com.popcorn.owncloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.popcorn.owncloud.bean.Administrator;
import com.popcorn.owncloud.bean.NormalUser;
import com.popcorn.owncloud.service.AdministratorService;
import com.popcorn.owncloud.service.NormalUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @RequestMapping("/login")
    public String adminLoginPage(Model model) {
        return "login";
    }

    /**
     * 登录验证模块
     */
    @PostMapping("/userLogin")
    public String userLogin(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String tableUserName = null;
        String tableUserPassword = null;
        List<NormalUser> normalUserList;
        JSONObject result = new JSONObject();
        //通过用户名从数据库中查询数据
        try{
            normalUserList = normalUserService.queryNormalUsersByUserName(userName);
            for (NormalUser normalUser:normalUserList) {
                //从数据库中获得的用户名和密码
                tableUserName = normalUser.getUserName();
                tableUserPassword = normalUser.getUserPassword();
            }


            if (Objects.equals(userName, tableUserName) && Objects.equals(userPassword, tableUserPassword)) {
                result.put("state","success");
                return result.toJSONString();
            }
            result.put("state","error");
            return  result.toJSONString();
        }catch (Exception e){
            result.put("state","error");
            return  result.toJSONString();
        }
    }

    /**
     * 管理员验证模块
     */
    @PostMapping("/adminLogin")
    public String adminLogin(HttpServletRequest request) {
        String adminName = request.getParameter("adminName");
        String adminPassword = request.getParameter("adminPassword");
        String tableAdminName = null;
        String tableAdminPassword = null;
        List<Administrator> administratorList;
        JSONObject result = new JSONObject();
        try {
            administratorList = administratorService.queryAdministratorsByAdminName(adminName);
            for (Administrator administrator : administratorList) {
                tableAdminName = administrator.getAdminName();
                tableAdminPassword = administrator.getAdminPassword();
            }

            if (Objects.equals(adminName, tableAdminName) && Objects.equals(adminPassword, tableAdminPassword)) {
                result.put("state","success");
                return result.toJSONString();
            }
            result.put("state","error");
            return  result.toJSONString();
        }catch (Exception e) {
            result.put("state","error");
            return result.toJSONString();
        }·4
    }

}
