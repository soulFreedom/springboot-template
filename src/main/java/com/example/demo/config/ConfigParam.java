package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author guohailong
 * @description: 展示yml文件配置属性与对象的映射关系
 * @date 2024/1/918:40
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config")
public class ConfigParam {
    private Integer id;
    private String  name;
}
