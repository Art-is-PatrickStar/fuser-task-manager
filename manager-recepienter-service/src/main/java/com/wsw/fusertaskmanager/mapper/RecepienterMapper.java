package com.wsw.fusertaskmanager.mapper;

import com.wsw.fusertaskmanager.domain.Recepienter;

public interface RecepienterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Recepienter record);

    int insertSelective(Recepienter record);

    Recepienter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recepienter record);

    int updateByPrimaryKey(Recepienter record);
}