package com.wsw.fusertaskmanager.mapper;

import com.wsw.fusertaskmanager.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author WangSongWen
 * @Date: Created in 16:07 2020/10/27
 * @Description:
 */
@Mapper
public interface OrderMapper {
    /**
     * 新增订单
     * @param order
     */
    int create(Order order);

    /**
     * 更改订单状态
     * @param userId
     * @param status
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
