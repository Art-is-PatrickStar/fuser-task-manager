package com.wsw.fusertaskmanager.rabbitmq;


import com.wsw.fusertaskmanager.service.LocalMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WangSongWen
 * @Date: Created in 10:01 2020/11/2
 * @Description: 消息发送服务
 */
@Service
@Slf4j
public class RabbitService implements ConfirmCallback, ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private LocalMessageService localMessageService;

    // 发送消息
    public void sendMessage(Long localMessageId, Object message) {
        // 当Mandatory参数设为true时，如果目的不可达，会发送消息给生产者，生产者通过一个回调函数来获取该信息
        this.rabbitTemplate.setMandatory(true);
        // 确认回调
        this.rabbitTemplate.setConfirmCallback(this);
        // 失败回退
        this.rabbitTemplate.setReturnCallback(this);
        // 用于确认之后更改本地消息状态或删除--本地消息id
        CorrelationData correlationData = new CorrelationData(String.valueOf(localMessageId));
        rabbitTemplate.convertAndSend("fanoutExchange", message, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("相关数据: " + correlationData + "\n" + "确认情况: " + ack + "\n" + "原因: " + cause);
        Long localMessageId = Long.valueOf(correlationData.getId());
        if (ack) {
            // 消息发送成功,更新本地消息为已成功发送状态或者直接删除该本地消息记录,剩余的由MQ投递到消费者端，消费者端需要进行幂等，避免产生脏数据
            localMessageService.updateLocalMessage(localMessageId, 1);
        } else {
            // 失败处理
            log.info("ack: " + ack);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String routingKey, String exchange) {
        log.info("消息: " + message + "\n" + "回应码: " + replyCode + "\n" + "回应信息: " + replyText + "\n" + "交换机: " + exchange + "\n"
                + "路由键: " + routingKey);
    }
}
