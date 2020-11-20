package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.mapper.TesterMapper;
import com.wsw.fusertaskmanager.service.TesterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 15:29 2020/11/20
 * @Description:
 */
@Service
public class TesterServiceImpl implements TesterService {
    @Resource
    private TesterMapper testerMapper;

    @Override
    public int insert(Long taskId, String taskName, String name, String remark) {
        return testerMapper.insert(taskId, taskName, name, remark);
    }
}
