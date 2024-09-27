package com.example.demo.business.impl;

import com.example.demo.business.Message;
import org.springframework.stereotype.Service;

/**
 * @author guohailong
 * @description: TODO
 * @date 2023/9/815:22
 */
@Service
public class SmsMessage implements Message {

    private String url = "abc";

    @Override
    public void send(String msg) {
        System.out.println("i'am smsMessage");
    }
}
