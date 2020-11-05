package com.wsw.fusertaskmanager.service.impl;

import com.rabbitmq.client.Channel;
import com.wsw.fusertaskmanager.mapper.StorageMapper;
import com.wsw.fusertaskmanager.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

    // 从RabbitMQ中接收消息
    @RabbitListener(queues = "queueStorage")  // 监听的队列名称queueStorage
    public void messageReceive(Channel channel, Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        try {
            log.info("wsw-cloud-storage-service接收到了消息: " + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrease(Long productId, Integer count) {
        storageMapper.decrease(productId, count);
    }
}
