package com.popcorn.owncloud.action;

import com.popcorn.owncloud.bean.NormalUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NormalUserMapper {
    @Select("select * from user_table where user_name = #{userName}")
    NormalUser queryNormalUsersByUserName(String userName);


    @Insert("insert into user_table(user_name, user_password, user_phone, user_register_timestamp) values (#{userName},#{userPassword},#{userPhoneNumber},#{userRegisterTimestamp})")
    Integer addNewNormalUser(NormalUser normalUser);

    @Update("update user_table set user_last_login_timestamp = #{userLastLoginTimestamp} where user_name = #{userName}")
    void addedLoginTimestamp(NormalUser normalUser);
}
