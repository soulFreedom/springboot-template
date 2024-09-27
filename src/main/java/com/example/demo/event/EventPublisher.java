package com.example.demo.event;

import com.google.common.eventbus.AsyncEventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 你的名字
 * @date 2024/9/19
 * @description
 */
@Component
@Slf4j
public class EventPublisher implements Publisher {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    @Autowired
    public void register(EventListener eventListener) {
        asyncEventBus.register(eventListener);
        log.info("注册事件成功");
    }

    @Override
    public void unRegister(EventListener eventListener) {

    }

    @Override
    public void publish(OrderEvent orderEvent) {
        asyncEventBus.post(orderEvent);
        log.info("发布时间成功");
    }
}
