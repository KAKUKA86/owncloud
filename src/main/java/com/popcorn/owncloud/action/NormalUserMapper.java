package com.popcorn.owncloud.action;

import com.popcorn.owncloud.bean.NormalUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NormalUserMapper {
    @Select("select * from user_table where user_name = #{userName}")
    NormalUser queryNormalUsersByUserName(String userName);


    @Insert("insert into user_table(user_name, user_password, user_phone, user_register_timestamp)"+
            "values (#{userName}, #{userPassword}, #{userPhone}, #{userRegisterTimestamp}")
    Integer addNewNormalUser(NormalUser normalUser);
}
