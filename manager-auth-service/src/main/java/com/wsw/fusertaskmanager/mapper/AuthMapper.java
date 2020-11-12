package com.wsw.fusertaskmanager.mapper;

import com.wsw.fusertaskmanager.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author WangSongWen
 * @Date: Created in 17:39 2020/11/12
 * @Description:
 */
@Mapper
public interface AuthMapper {
    @Select(value = "select * from user where username=#{u} and password=#{p}")
    User getUserByUAP(@Param("u") String username, @Param("p") String password);
}
