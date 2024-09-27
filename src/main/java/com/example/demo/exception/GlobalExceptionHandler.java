package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author guohailong
 * @description: TODO
 * @date 2023/6/2619:16
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex) {
        System.out.println("程序出现异常:" + ex);
    }
}
