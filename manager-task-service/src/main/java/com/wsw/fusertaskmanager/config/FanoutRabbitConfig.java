package com.wsw.fusertaskmanager.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangSongWen
 * @Date: Created in 17:18 2020/10/30
 * @Description: 交换器类型：Fanout
 */
@Configuration
public class FanoutRabbitConfig {
    //队列1
    @Bean
    Queue queueTester(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("directQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("queueTester", true);
    }

    //队列2
    @Bean
    Queue queueRecepienter(){
        return new Queue("queueRecepienter", true);
    }

    //Fanout交换器
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange", true, false);
    }

    @Bean
    Binding bindingExchangeTester(){
        return BindingBuilder.bind(queueTester()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeRecepienter(){
        return BindingBuilder.bind(queueRecepienter()).to(fanoutExchange());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //必须为true,否则无法触发returnedMessage回调，消息丢失
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

}
