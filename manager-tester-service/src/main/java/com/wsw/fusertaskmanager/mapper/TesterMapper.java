package com.wsw.fusertaskmanager.mapper;

import com.wsw.fusertaskmanager.domain.Tester;

public interface TesterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tester record);

    int insertSelective(Tester record);

    Tester selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tester record);

    int updateByPrimaryKey(Tester record);
}