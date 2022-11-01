package com.popcorn.owncloud.action;

import com.popcorn.owncloud.bean.CollectFile;
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
    @Select("select * from file_table where file_id = #{fileId}")
    File queryFileById(Integer fileId);
    @Insert("insert into collect_table(file_id) values (#{fileId})")
    void insertCollectFile(File file);
    @Select("select * from collect_table")
    List<CollectFile> queryCollectFileList();
}
