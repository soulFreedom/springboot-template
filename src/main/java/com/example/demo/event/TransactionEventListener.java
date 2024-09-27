package com.example.demo.event;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 你的名字
 * @date 2024/9/20
 * @description
 */

@Component
@Slf4j
public class TransactionEventListener implements EventListener {

    @Subscribe
    public void orderPaySucced(OrderEvent orderEvent) {
        log.info("orderPaySucced 事件 处理开始" );
        orderEvent.doEvent();
        log.info("orderPaySucced 事件 处理结束" );
    }
}
