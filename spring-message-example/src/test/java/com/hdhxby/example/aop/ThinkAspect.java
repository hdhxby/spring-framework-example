package com.hdhxby.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ThinkAspect {

    private Logger logger = LoggerFactory.getLogger(ThinkAspect.class);

    @Pointcut("execution(* com.hdhxby.example.entity.Person.speech(..))")
    public void point(){};


    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint){
        try{
            logger(joinPoint).info("before");
            joinPoint.proceed();
            logger(joinPoint).info("after");
        }  catch (Throwable throwable) {
            logger(joinPoint).error("error", throwable);
        }
    };

    @Around("point()")
    public void before(){
        logger.info("before");
    };

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }
}
