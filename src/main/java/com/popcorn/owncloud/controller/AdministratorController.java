package com.popcorn.owncloud.controller;


import com.alibaba.fastjson.JSONObject;
import com.popcorn.owncloud.bean.Administrator;
import com.popcorn.owncloud.bean.NormalUser;
import com.popcorn.owncloud.service.AdministratorService;
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
    @RequestMapping("/admin/adminHomePage")
    public String adminHomePage() {
        return "/admin/adminHomePage";
    }

    @Resource
    private AdministratorService administratorService;

    @Resource
    private NormalUserService normalUserService;

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
        System.out.println(administratorList.get(0).getAdminPhoneNumber());
        model.addAttribute("tables", administratorList);
        return "/admin/admin-list";
    }

    /**
     * level0管理员增删改查
     */
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

    @RequestMapping("/editAdminUser")
    public String editAdminUser(Administrator administrator) {
        System.out.println(administrator.getAdminId());
        System.out.println(administrator.getAdminName());
        System.out.println(administrator.getAdminPassword());
        System.out.println(administrator.getAdminLevel());
        System.out.println(administrator.getAdminPhoneNumber());
        administratorService.updateAdmin(administrator);
        return "redirect:/admin/admin-list";
    }
}
