package com.popcorn.owncloud.controller;


import com.alibaba.fastjson.JSONObject;
import com.popcorn.owncloud.bean.Administrator;
import com.popcorn.owncloud.bean.File;
import com.popcorn.owncloud.bean.NormalUser;
import com.popcorn.owncloud.service.AdministratorService;
import com.popcorn.owncloud.service.FileService;
import com.popcorn.owncloud.service.NormalUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class AdministratorController {

    /**
     * 跳转admin用户登陆界面
     *
     * @return
     */
    @RequestMapping("/admin/login")
    public String adminLogin() {
        return "/admin/login";
    }

    /**
     * 登陆成功后跳转admin主页页面
     *
     * @return
     */

    @Resource
    private AdministratorService administratorService;

    @Resource
    private NormalUserService normalUserService;

    @Resource
    private FileService fileService;

    /**
     * 管理员普通用户查询
     *
     * @return
     */
    @RequestMapping("/admin/user-list")
    public String queryNormalUser(Model model) {
        List<NormalUser> normalUserList;
        normalUserList = normalUserService.queryNormalUserList();
        model.addAttribute("normalUsers", normalUserList);
        return "/admin/user-list";
    }

    @RequestMapping("/admin/admin-list")
    public String queryAdminUser(Model model) {
        List<Administrator> administratorList;
        administratorList = administratorService.queryAdministratorList();
        model.addAttribute("tables", administratorList);
        return "/admin/admin-list";
    }

    @RequestMapping("/admin/adminFileAudit")
    public String auditFile(Model model) {
        List<File> fileList;
        fileList = fileService.queryFileList();
        model.addAttribute("files", fileList);
        return "/admin/adminFileAudit";
    }


    @PostMapping("/addAdminUser")
    @ResponseBody
    public String addAdminUser(Administrator administrator) {
        int cnt = administratorService.addAdminUser(administrator);
        JSONObject result = new JSONObject();
        if (cnt > 0) result.put("state", "success");
        return result.toJSONString();
    }

    @GetMapping("/deleteAdminUser")
    public String deleteAdminUser(@RequestParam("id") int id) {
        administratorService.deleteAdminUser(id);
        return "redirect:/admin/admin-list";
    }

    @GetMapping("/toEditPage")
    public String toEditPage(Model model, int id) {
        Administrator administrator = administratorService.queryAdministratorByAdminId(id);
        model.addAttribute("administrator", administrator);
        return "admin/adminEdit";
    }

    @GetMapping("/toEditNormalUserPage")
    public String toEditNormalUserPage(Model model, int id) {
        NormalUser normalUser = normalUserService.queryNormalUsersByUserId(id);
        model.addAttribute("normalUser", normalUser);
        return "admin/normalUserEdit";

    }

    @RequestMapping("/editAdminUser")
    public String editAdminUser(Administrator administrator) {
        System.out.println("adminId: " + administrator.getAdminId());
        System.out.println("adminName:" + administrator.getAdminName());
        System.out.println("adminPassword: " + administrator.getAdminPassword());
        System.out.println("adminLevel: " + administrator.getAdminLevel());
        System.out.println("adminPhone: " + administrator.getAdminPhoneNumber());
        administratorService.updateAdmin(administrator);
        return "redirect:/admin/admin-list";
    }
    @RequestMapping("/editNormalUser")
    public String editNormalUser(NormalUser normalUser) {
        normalUserService.updateNormalUser(normalUser);
        return "redirect:/admin/user-list";
    }


}
