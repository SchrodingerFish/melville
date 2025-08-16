package com.cn.melville.service.impl;

import com.cn.melville.service.MessageProducer;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageProducer messageProducer;

    public MessageService(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void sendMessage(String message) {
        messageProducer.sendMessage(message);
    }

    // 可以添加更多方法，例如发送不同类型的消息、订阅消息等
}
