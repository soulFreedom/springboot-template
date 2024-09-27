package com.example.demo.event;

/**
 * @author 你的名字
 * @date 2024/9/20
 * @description
 */
public interface Publisher {
    public void register(EventListener eventListener);
    public void unRegister(EventListener eventListener);
    public void publish(OrderEvent orderEvent);
}
