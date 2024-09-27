package com.example.demo.event;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 你的名字
 * @date 2024/9/20
 * @description
 */

@Component
@Slf4j
@Data
public class OrderEvent {

    @Autowired
    private Order order;

    public void doEvent() {
        log.info("order info =" + order.toString());
    }
}
