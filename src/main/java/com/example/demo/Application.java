package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;


@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // 获取当前项目的实际类路径
        String classpath = System.getProperty("java.class.path");
        System.out.println("Classpath: " + classpath);
    }

}
