package com.popcorn.owncloud.action;

import com.popcorn.owncloud.bean.NormalUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NormalUserMapper {
    @Select("select * from user_table where user_name = #{userName}")
    List<NormalUser> queryNormalUsersByUserName(String userName);
}
