package com.example.demo.domain;

import com.example.demo.controller.RequestModeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author guohailong
 * @description: 研究Bean的生命周期
 * @date 2023/7/1915:27
 */
//@Component
public class BeanLifeCycle implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {
    Logger logger = LoggerFactory.getLogger(BeanLifeCycle.class);

    @Override
    public void setBeanName(String beanName) {
        logger.info("①setBeanName={}",beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("②setBeanFactory={}",beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("③setApplicationContext={}",applicationContext);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("④postProcessBeforeInitialization，bean={}，beanName={}",bean,beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("⑤postProcessAfterInitialization，bean={}，beanName={}",bean,beanName);
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("⑥afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        logger.info("⑦init");
    }


    @Override
    public void destroy() throws Exception {
        logger.info("⑧对象被销毁");
    }
}
