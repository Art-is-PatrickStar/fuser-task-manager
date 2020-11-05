package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.mapper.StorageMapper;
import com.wsw.fusertaskmanager.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author WangSongWen
 * @Date: Created in 17:27 2020/10/27
 * @Description:
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrease(Long productId, Integer count) {
        storageMapper.decrease(productId, count);
    }
}
