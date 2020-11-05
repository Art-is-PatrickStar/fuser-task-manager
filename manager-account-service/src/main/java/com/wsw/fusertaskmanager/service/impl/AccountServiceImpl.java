package com.wsw.fusertaskmanager.service.impl;

import com.rabbitmq.client.Channel;
import com.wsw.fusertaskmanager.mapper.AccountMapper;
import com.wsw.fusertaskmanager.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * @Author WangSongWen
 * @Date: Created in 17:59 2020/10/27
 * @Description:
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    // 从RabbitMQ中接收消息
    @RabbitListener(queues = "queueAccount")  // 监听的队列名称queueAccount
    public void messageReceive(Channel channel, Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        //JSONObject jsonObject = JSONObject.parseObject(msg);
        try {
            log.info("wsw-cloud-account-service接收到了消息: " + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrease(Long userId, BigDecimal money) {
        accountMapper.decrease(userId, money);
    }
}
