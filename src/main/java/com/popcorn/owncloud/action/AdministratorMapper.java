package com.popcorn.owncloud.action;

import com.popcorn.owncloud.bean.Administrator;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdministratorMapper {
    @Select("select * from admin_table where admin_name = #{adminName}")
    Administrator queryAdministratorsByAdminName(String adminName);

    @Select("select * from admin_table")
    List<Administrator> queryAdministratorList();
    @Select("select * from admin_table where admin_id = #{adminId}")
    Administrator queryAdministratorsByAdminId(int adminId);

    @Insert("insert into admin_table (admin_level, admin_name, admin_password, admin_number) " +
            "values (1, #{adminName}, #{adminPassword}, #{admin_number})")
    int addAdminUser(Administrator administrator);

    @Delete("delete from admin_table where admin_id = #{adminId}")
    void deleteAdmin(Integer adminId);

    @Update("update admin_table set admin_level = #{adminLevel}, admin_name = #{adminName}, admin_password = #{adminPassword} admin_phone_number = #{adminPhoneNumber} " +
            "where admin_id = #{adminId}")
    int updateAdminUser(Administrator administrator);


}
