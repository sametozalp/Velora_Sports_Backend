package com.ozalp.Velora.Sports.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LoggingAspect {

    @Before("execution (* com.ozalp.Velora.Sports.business.concretes.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //System.out.println("called: " + className + "." + methodName);
    }
}
