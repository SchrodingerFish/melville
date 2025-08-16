package com.cn.melville.service;

public interface MessageProducer {

    void sendMessage(String message);
    void sendMessage(String exchange, String routingKey, String message); // 更灵活的方法

}
