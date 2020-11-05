package com.wsw.fusertaskmanager.service;

import java.math.BigDecimal;

/**
 * @Author WangSongWen
 * @Date: Created in 17:58 2020/10/27
 * @Description:
 */
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
