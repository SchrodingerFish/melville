package com.cn.melville.service.impl;

import com.cn.melville.service.MessageProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer implements MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;
    @Value("${rabbitmq.queue}")
    private String queueName;
    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    @Override
    public void sendMessage(String message) {
        sendMessage(exchangeName, routingKey, message); // 使用默认的交换器和路由键
    }

    @Override
    public void sendMessage(String exchange, String routingKey, String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println(" [√] Sent '" + message + "' to exchange '" + exchange + "' with routingKey '" + routingKey + "'");
    }
}
