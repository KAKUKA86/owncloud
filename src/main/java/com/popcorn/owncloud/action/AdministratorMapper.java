package com.popcorn.owncloud.action;

import com.popcorn.owncloud.bean.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdministratorMapper {
    @Select("select * from admin_table where admin_name = #{adminName}")
    List<Administrator> queryAdministratorsByAdminName(String adminName);
}
