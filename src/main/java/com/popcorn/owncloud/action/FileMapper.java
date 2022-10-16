package com.popcorn.owncloud.action;

import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpServletRequest;

@Mapper
public interface FileMapper {
    String uploadMulti(HttpServletRequest request, String filePath);
}
