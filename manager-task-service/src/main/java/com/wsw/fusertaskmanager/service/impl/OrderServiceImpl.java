package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.mapper.OrderMapper;
import com.wsw.fusertaskmanager.service.AccountService;
import com.wsw.fusertaskmanager.service.OrderService;
import com.wsw.fusertaskmanager.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author WangSongWen
 * @Date: Created in 16:35 2020/10/27
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Order order){
        // 1. 创建订单
        int result = orderMapper.create(order);

        // 2. 减库存
        storageService.decrease(order.getProductId(), order.getCount());

        // 3. 扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());

        // 4. 修改订单状态
        orderMapper.update(order.getUserId(), 0);
    }

}
