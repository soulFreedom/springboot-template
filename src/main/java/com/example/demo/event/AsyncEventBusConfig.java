package com.example.demo.event;

import com.google.common.eventbus.AsyncEventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 你的名字
 * @date 2024/9/20
 * @description
 */

@Configuration
@Slf4j
public class AsyncEventBusConfig {

    @Bean(name = "asynEventBus")
    public AsyncEventBus asyncEventBusConfig() {
        ThreadPoolTaskExecutor executor = executor();
        return new AsyncEventBus(executor);
    }

    @Bean
    public ThreadPoolTaskExecutor executor () {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(1000);
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.info("拒绝策略开始生效了，" + executor);
                throw new RejectedExecutionException("task " + r.toString() + "rejected from " + executor.toString());
            }
        });

        return executor;
    }

}
