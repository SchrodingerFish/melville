package com.cn.melville.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncMessageProcessor {

    @Async
    public void processMessage(String message) {
        // 异步处理消息的业务逻辑
        System.out.println("Processing message asynchronously: " + message);
    }
}

