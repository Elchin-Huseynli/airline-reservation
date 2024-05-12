package com.airline.user_ms.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;


@Aspect
@Slf4j
@Configuration
public class UserLog {


    @Before(value = "allController()")
    public void logAllUserController(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();

        String methodName = signature.getName(); // method name


        log.info("User-ms:: method name {} , request parameters {}", methodName, joinPoint.getArgs());
    }


    @Pointcut("@annotation(UserProcess)")
    public void allController(){}
}
