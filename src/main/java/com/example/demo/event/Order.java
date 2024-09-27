package com.example.demo.event;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 你的名字
 * @date 2024/9/20
 * @description
 */

@Component
@Data
public class Order {
    private String orderId;
    private String orderDate;
    private Date  dateTime;


    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
