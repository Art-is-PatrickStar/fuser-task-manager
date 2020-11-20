package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.mapper.RecepienterMapper;
import com.wsw.fusertaskmanager.service.RecepienterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 14:21 2020/11/20
 * @Description:
 */
@Service
public class RecepienterServiceImpl implements RecepienterService {
    @Resource
    private RecepienterMapper recepienterMapper;

    @Override
    public int insert(Long taskId, String taskName, String name, String remark) {
        return recepienterMapper.insert(taskId, taskName, name, remark);
    }
}
