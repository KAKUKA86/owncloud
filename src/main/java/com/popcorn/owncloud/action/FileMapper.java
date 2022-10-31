package com.popcorn.owncloud.action;

import com.popcorn.owncloud.bean.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper
public interface FileMapper {
//    String uploadMulti(HttpServletRequest request, String filePath);

    @Insert("insert into file_table( file_name, file_timestamp, file_url, file_type, file_size) " +
            "values (#{fileName}, #{fileTimestamp}, #{fileUrl}, #{fileType}, #{fileSize})")
    void uploadMulti(File saveFile);

    @Select("select * from file_table")
    List<File> queryFileList();
}
