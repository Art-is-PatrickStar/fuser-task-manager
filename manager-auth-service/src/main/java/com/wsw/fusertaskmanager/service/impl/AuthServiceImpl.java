package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.dto.User;
import com.wsw.fusertaskmanager.mapper.AuthMapper;
import com.wsw.fusertaskmanager.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 17:45 2020/11/12
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthMapper authMapper;

    @Override
    public User auth(String username, String password) {
        User user = authMapper.getUserByUAP(username, password);
        return user;
    }
}
