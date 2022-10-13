package com.popcorn.owncloud.service.impl;

import com.popcorn.owncloud.action.NormalUserMapper;
import com.popcorn.owncloud.bean.NormalUser;
import com.popcorn.owncloud.service.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormalUserServiceImpl implements NormalUserService {

    @Autowired
    private NormalUserMapper userMapper;

    @Override
    public NormalUser queryNormalUsersByUserName(String userName) {
        return userMapper.queryNormalUsersByUserName(userName);
    }

    @Override
    public Integer userSigIn(NormalUser normalUser) {
        return userMapper.addNewNormalUser(normalUser);
    }

    @Override
    public void addedLogonTimestamp(NormalUser normalUser) {
        userMapper.addedLoginTimestamp(normalUser);
    }
}
