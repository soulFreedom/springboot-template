package com.example.demo.controller;


import com.example.demo.annotation.PermissionAnnotation;
import com.example.demo.business.Message;
import com.example.demo.business.impl.EmailMessage;
import com.example.demo.config.ConfigParam;
import com.example.demo.domain.BusiData;
import com.example.demo.domain.Result;
import com.example.demo.event.EventPublisher;
import com.example.demo.event.Order;
import com.example.demo.event.OrderEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author guohailong
 * @description:
 * @date 2023/6/2520:35
 *
 * 模拟get post 请求，demo 样例
 *
 */

@RestController
@RequestMapping("/req")
@EnableSwagger2
@Api(value = "展示HTTP 调用方式请求接口合集",tags = "用户操作接口")
public class RequestModeController {

    Logger logger = LoggerFactory.getLogger(RequestModeController.class);

    @Autowired
    private ConfigParam configParam;

    @Autowired
    @Qualifier("smsMessage")
    private Message message;

//    @Autowired
//    private EmailMessage emailMessage;

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private OrderEvent orderEvent;

    @Autowired
    private Order order;


    /**
     * get请求，url传参
     * http://localhost:6688/springboot-template/req/get/param?username=123&password=1234
     * 注意@RequestParam使用了defaultValue required 只能为false,指定为true不生效。
     */
    @GetMapping("/get/param")
    @PermissionAnnotation
    @ApiOperation(value = "HTTP GET 请求传参展示")
    public Result printUrlParams(@RequestParam(value = "userName",required = true,defaultValue = "abc") String userName, @RequestParam("password") String password) {
//        logger.info("userName={} password={}",userName,password);
//        message.toString();

        order.setOrderId("12312");
        order.setOrderDate("20240807");
        order.setDateTime(new Date());

        orderEvent.setOrder(order);

        eventPublisher.publish(orderEvent);
        logger.info("我看看是不是异步的");

        return new Result("0000","交易成功");
    }

    /**
     * get 请求，在Path中获取参数。
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/get/pathParams/{id}/{name}")
    public Result getPathParams(@PathVariable String id, @PathVariable String name) {
        logger.info("id={},name={}",id,name);
        return new Result("0000","交易成功");
    }

    // 打印请求参数
    @PostMapping("/post/body")
    @ApiOperation(value = "HTTP POST 请求展示")
    public Result printPostParams(@RequestBody BusiData busiData) {
        logger.info("busiDate.userName={}, busiDate.password={}",busiData.getUserName(),busiData.getPassword());
        return new Result("0000","交易成功");
    }

    /**
     *
     * @return
     */
    @GetMapping("/get/props")
    @ApiOperation(value = "YML配置文件读取展示")
    public Result printUrlParams() {

        logger.info("configParam.id:{},configParam.name:{}",configParam.getId(),configParam.getName());

        return new Result("0000","交易成功");
    }

    public static void main(String[] args) {

    }

}
