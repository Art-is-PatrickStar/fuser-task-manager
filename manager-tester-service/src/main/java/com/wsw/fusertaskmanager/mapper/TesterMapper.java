package com.wsw.fusertaskmanager.mapper;

import com.wsw.fusertaskmanager.domain.Tester;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TesterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(@Param("name") String name, @Param("remark") String remark);

    int insertSelective(Tester record);

    Tester selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tester record);

    int updateByPrimaryKey(Tester record);
}