package com.wsw.fusertaskmanager.mapper;

import com.wsw.fusertaskmanager.domain.LocalMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 16:10 2020/11/2
 * @Description:
 */
@Mapper
public interface LocalMessageMapper {
    void insertLocalMessage(LocalMessage localMessage);

    void updateLocalMessage(@Param("localMessageId") Long localMessageId, @Param("status") Integer status);

    List<LocalMessage> selectFailMessage(Integer status);
}
