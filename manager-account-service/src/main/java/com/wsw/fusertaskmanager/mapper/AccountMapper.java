package com.wsw.fusertaskmanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Author WangSongWen
 * @Date: Created in 17:50 2020/10/27
 * @Description:
 */
@Mapper
public interface AccountMapper {
    /**
     * 扣减账户余额
     * @param userId
     * @param money
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
