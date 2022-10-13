package com.popcorn.owncloud.service;

import com.popcorn.owncloud.bean.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator queryAdministratorsByAdminName(String adminName);

    List<Administrator> queryAdministratorList();

    int addAdminUser(Administrator administrator);

    void deleteAdminUser(Integer adminId);

    int updateAdmin(Administrator administrator);
}
