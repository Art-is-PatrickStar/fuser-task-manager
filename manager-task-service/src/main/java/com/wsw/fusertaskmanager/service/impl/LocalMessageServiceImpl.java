package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.domain.LocalMessage;
import com.wsw.fusertaskmanager.mapper.LocalMessageMapper;
import com.wsw.fusertaskmanager.service.LocalMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:17 2020/11/2
 * @Description:
 */
@Service
public class LocalMessageServiceImpl implements LocalMessageService {
    @Autowired
    private LocalMessageMapper localMessageMapper;

    @Override
    public void insertLocalMessage(LocalMessage localMessage) {
        localMessageMapper.insertLocalMessage(localMessage);
    }

    @Override
    public void updateLocalMessage(Long localMessageId, Integer status) {
        localMessageMapper.updateLocalMessage(localMessageId, status);
    }

    @Override
    public List<LocalMessage> selectFailMessage(Integer status) {
        return localMessageMapper.selectFailMessage(status);
    }
}
