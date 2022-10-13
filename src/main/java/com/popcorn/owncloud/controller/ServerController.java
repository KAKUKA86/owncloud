package com.popcorn.owncloud.controller;

import com.popcorn.owncloud.bean.NormalUser;
import com.popcorn.owncloud.service.NormalUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class ServerController {
    @Resource
    private NormalUserService service;

    /**
     * 自动清除不活跃用户（释放空间还未实现）
     */
    @PostMapping
    public void cleanUpInactiveUsers(){
        List<NormalUser> normalUsers;
        normalUsers = service.queryNormalUserList();//查询到数据并创建列表
        Integer[] userIds = new Integer[normalUsers.size()];
        int o = 0;
        for (NormalUser normalUser : normalUsers) {
            if (System.currentTimeMillis() - normalUser
                    .getUserLastLoginTimestamp() > 60 * 60 * 24 * 180) {
                o++;
                userIds[o] = normalUser.getUserID();
            }
        }
        for (Integer userId : userIds) {
            service.deleteUserById(userId);
        }
    }
}
