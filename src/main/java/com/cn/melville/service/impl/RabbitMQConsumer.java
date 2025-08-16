package com.cn.melville.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private final AsyncMessageProcessor asyncMessageProcessor;

    public RabbitMQConsumer(AsyncMessageProcessor asyncMessageProcessor) {
        this.asyncMessageProcessor = asyncMessageProcessor;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String message) {
        asyncMessageProcessor.processMessage(message); // 异步处理
    }
}
