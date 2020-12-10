package com.wsw.fusertaskmanager.service.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.wsw.fusertaskmanager.mapper.TesterMapper;
import com.wsw.fusertaskmanager.service.TesterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 15:29 2020/11/20
 * @Description:
 */
@Service
@Slf4j
public class TesterServiceImpl implements TesterService {
    @Resource
    private TesterMapper testerMapper;

    @RabbitHandler
    @RabbitListener(queues = "queueTester")
    public void receiveMessage(Message message, Channel channel, Map<String, Object> messageMap) throws IOException {
        try {
            log.info("manager-tester-service接收到了消息: " + JSONObject.toJSONString(messageMap));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            Long taskId = MapUtil.getLong(messageMap, "taskId");
            String taskName = MapUtil.getStr(messageMap, "taskName");
            String testerName = MapUtil.getStr(messageMap, "testerName");
            String remark = MapUtil.getStr(messageMap, "remark");
            if (null != taskId && StringUtils.isNotBlank(taskName) && StringUtils.isNotBlank(testerName)){
                int result = insert(taskId, taskName, testerName, remark);
                if (result >= 1){
                    log.info("manager-tester-service插入数据成功!");
                }
            }
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.info("消息已重复处理失败,拒绝再次接收");
                // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.info("消息即将再次返回队列处理");
                // requeue为是否重新回到队列，true重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            log.error(e.getMessage());
        }
    }

    @Override
    public int insert(Long taskId, String taskName, String name, String remark) {
        return testerMapper.insert(taskId, taskName, name, remark);
    }
}
