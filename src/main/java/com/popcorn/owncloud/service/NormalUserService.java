package com.popcorn.owncloud.service;

import com.popcorn.owncloud.bean.NormalUser;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface NormalUserService {
    NormalUser queryNormalUsersByUserName(String username);

    NormalUser queryNormalUsersByUserId(int id);

    Integer userSigIn(NormalUser normalUser);

    void addedLogonTimestamp(NormalUser normalUser);

    List<NormalUser> queryNormalUserList();

    void deleteUserById(Integer userId);

    void updateNormalUser(NormalUser normalUser);
}
