package com.ozalp.Velora.Sports.aop;

import org.aspectj.lang.JoinPoint;

public interface LoggingService {

    void logBefore(JoinPoint joinPoint);
}
