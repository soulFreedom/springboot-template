package com.example.demo.business.impl;

import com.example.demo.business.Message;
import org.springframework.stereotype.Service;

/**
 * @author guohailong
 * @description: TODO
 * @date 2023/9/815:39
 */
@Service
public class EmailMessage implements Message {
    @Override
    public void send(String msg) {
        System.out.println("i am emailMessage");
    }
}
