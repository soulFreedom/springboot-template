package com.example.demo.aspect;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.util.Json;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author 你的名字
 * @date 2024/6/20
 * @description
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    //写法1：先定义PointCut，然后再使用
    //特定切点表达式框定的范围
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    private void logExecuteTimePointcut() {};

    @Pointcut("execution(* com.example.demo.business.*.*(..))")
    private void verifyProxyMode(){};

    //切入所有加了@GetMapping注解的方法
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void getMethodLogPointcut() {};

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void postMethodLogPointcut(){}

    @Pointcut("@annotation(com.example.demo.annotation.PermissionAnnotation)")
    private void permissionCheckPointcut(){}


    @Before("postMethodLogPointcut()")
    public void postMethodLog() {
        log.info("只有加了@PostMapping注解的方法才能看到我");
    }

//    @Before("getMethodLogPointcut()")
    @Before("verifyProxyMode()")
    public void getMethodLog() {
        log.info("只有加了@GetMapping注解的方法才能看到我-before");
    }

//    @Before("permissionCheckPointcut()")
    public void permissionCheck() {
        log.info("只有加了@PermissionAnnotation注解的方法才能看到我");
    }

//    @Around("permissionCheckPointcut()")
    public Object permissionCheckSecond(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around-before");

        Signature signature = joinPoint.getSignature();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestUrl = request.getRequestURI().toString();

        Object result = joinPoint.proceed();
        log.info("around-after");

//        log.info("当前的请求地址{}",requestUrl);

        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            Object[] objects = joinPoint.getArgs();

            String[] parameterNames = methodSignature.getParameterNames();
            if (parameterNames != null) {
                for (int i = 0; i <parameterNames.length ; i++) {
//                    log.info("方法名:{},参数名{},参数值{}" ,method.getName(),parameterNames[i],objects[i]);
                }
            }
        }



        return result;
    }


//    @Around("logExecuteTimePointcut()")
    public Object logExecuteTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        long executeTime = endTime - startTime;

        log.info("方法" + joinPoint.getSignature() +" executed(执行时间) in " + executeTime);

        return result;
    }

//    @After("getMethodLogPointcut()")
    public void afterProcess() {
        log.info("after 处理.....");
    }

//    @AfterReturning(pointcut = "getMethodLogPointcut()",returning = "result")
    public void doAfterReturning(JoinPoint jointPoint, Object result) {
        log.info("AfterReturning...对返回参数进行业务上的增强：{}", JSONObject.from(result));
    }

//    @AfterThrowing(pointcut = "getMethodLogPointcut()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Throwable ex) {
        log.info("执行方法{}，出错，异常为{}",joinPoint.getSignature().getName(),ex);
    }


    //写法2：不单独定义PointCut 直接写到一起
    /*@Around("execution(* com.example.demo.controller.*.*(..))")
    public Object logExecuteTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        long executeTime = endTime - startTime;

        System.out.println(joinPoint.getSignature() +" executed in " + executeTime);

        return result;
    }*/
}
