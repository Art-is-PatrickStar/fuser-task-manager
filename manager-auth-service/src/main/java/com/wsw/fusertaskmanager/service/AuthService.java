package com.wsw.fusertaskmanager.service;

import com.wsw.fusertaskmanager.dto.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author WangSongWen
 * @Date: Created in 17:45 2020/11/12
 * @Description:
 */
public interface AuthService {
    User auth(String username, String password);
}
