package com.cn.melville.events;

import com.cn.melville.service.MessageProducer;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    private final MessageProducer messageProducer;

    public OrderEventProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @EventListener
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        String orderId = event.getOrderId();
        String json=event.getSource().toString();
        // 发送消息到 RabbitMQ，例如使用 orderId 作为消息内容
        messageProducer.sendMessage("test.exchange", "test.routing.key", json);
    }
}
