package com.popcorn.owncloud.service;

import com.popcorn.owncloud.bean.Administrator;

import java.util.List;

public interface AdministratorService {
    List<Administrator> queryAdministratorsByAdminName(String adminName);

    List<Administrator> queryNormalUser();
}
