package com.cn.melville.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.cn.melville.events.OrderCreatedEvent;
import com.cn.melville.model.User;
import com.cn.melville.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;
    private final Gson gson;

    public OrderService(ApplicationEventPublisher eventPublisher, Gson gson) {
        this.eventPublisher = eventPublisher;
        this.gson = gson;
    }

    public void createOrder(String orderId) {
        //  创建订单的业务逻辑
        // ...
        List<User> users = userRepository.findAll();
        if (CollUtil.isEmpty(users)) {
            throw new IllegalArgumentException("User not found for order ID: " + orderId);
        }

        String json = StrUtil.EMPTY_JSON;
        //  发布订单创建事件
        for (int i = 0; i < users.size(); i++) {
            json=gson.toJson(users.get(i));
            eventPublisher.publishEvent(new OrderCreatedEvent(json,orderId+i));
        }
    }
}
