package com.popcorn.owncloud.service.impl;

import com.popcorn.owncloud.action.AdministratorMapper;
import com.popcorn.owncloud.bean.Administrator;
import com.popcorn.owncloud.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;
    @Override
    public List<Administrator> queryAdministratorsByAdminName(String adminName) {
        return administratorMapper.queryAdministratorsByAdminName(adminName);
    }

    @Override
    public List<Administrator> queryNormalUser() {
        return administratorMapper.queryNormalUser();
    }

}
