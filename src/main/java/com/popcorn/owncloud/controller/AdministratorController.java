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
import java.util.List;


@Controller
public class AdministratorController {

    /**
     * 跳转admin用户登陆界面
     * @return
     */
    @RequestMapping("/admin/login")
    public String adminLogin() {
        return "/admin/login";
    }
    /**
     * 登陆成功后跳转admin主页页面
      * @return
     */
    @RequestMapping("/admin/adminHomePage")
    public String adminHomePage(){
        return "/admin/adminHomePage";
    }
    @Resource
    private AdministratorService administratorService;

    @Resource
    private NormalUserService normalUserService;

    /**
     * 管理员普通用户查询
     * @return
     */
    @PostMapping("/queryNormalUser")
    public String queryNormalUser() {
        List<NormalUser> normalUserList;
        JSONObject result = new JSONObject();
        normalUserList = normalUserService.queryNormalUserList();
        result.put("rows", normalUserList);
        return result.toJSONString();
    }

    /**
     * level0管理员增删改查
     */
    @PostMapping("/addAdminUser")
    @ResponseBody
    public String addAdminUser(Administrator administrator){
        int cnt = administratorService.addAdminUser(administrator);
        JSONObject result = new JSONObject();
        if (cnt > 0) result.put("state","success");
        return result.toJSONString();
    }

    @PostMapping("/deleteAdminUser")
    @ResponseBody
    public String deleteAdminUser(HttpServletRequest request) {
        String [] adminIds = request.getParameterValues("ids");
        for (String adminId : adminIds) {
            administratorService.deleteAdminUser(Integer.parseInt(adminId));
        }
        JSONObject result = new JSONObject();
        result.put("state","success");
        return result.toJSONString();
    }
    @PostMapping("/updateAdminUser")
    @ResponseBody
    public String updateAdminUser(Administrator administrator){
        int cnt = administratorService.updateAdmin(administrator);
        JSONObject result = new JSONObject();
        if (cnt > 0)
            result.put("state","success");
        return result.toJSONString();
    }
    @PostMapping("/queryAdminUser")
    @ResponseBody
    public String queryAdminUser() {
        List<Administrator> administratorList;
        JSONObject result = new JSONObject();
        administratorList = administratorService.queryAdministratorList();
        result.put("rows",administratorList);
        return result.toJSONString();
    }






}
