package com.wsw.fusertaskmanager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wsw.fusertaskmanager.domain.LocalMessage;
import com.wsw.fusertaskmanager.domain.Order;
import com.wsw.fusertaskmanager.mapper.OrderMapper;
import com.wsw.fusertaskmanager.rabbitmq.RabbitService;
import com.wsw.fusertaskmanager.service.AccountService;
import com.wsw.fusertaskmanager.service.LocalMessageService;
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
    @Autowired
    private RabbitService rabbitService;
    @Autowired
    private LocalMessageService localMessageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Order order){
        // 1. 创建订单
        int result = orderMapper.create(order);

        // 向RabbitMQ中发消息
        String message = JSONObject.toJSONString(order);
        if (result == 1){
            // 本地消息表添加本地消息
            LocalMessage localMessage = new LocalMessage();
            localMessage.setLocalMessageId(order.getId());
            localMessage.setMessage(message);
            localMessage.setStatus(0);
            localMessageService.insertLocalMessage(localMessage);

            // 发消息
            rabbitService.sendMessage(order.getId(), order);
        }

        // 2. 减库存
        storageService.decrease(order.getProductId(), order.getCount());

        // 3. 扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());

        // 4. 修改订单状态
        orderMapper.update(order.getUserId(), 0);
    }

}
