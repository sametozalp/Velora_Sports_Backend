package com.ozalp.Velora.Sports.aop;

import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.dataAcess.LogRepository;
import com.ozalp.Velora.Sports.entities.concretes.Log;
import com.ozalp.Velora.Sports.entities.enums.ExceptionLogStatus;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Service
@AllArgsConstructor
public class LoggingAspect {

    private final LogRepository logRepository;
    private final UserService userService;

    @Before("execution (* com.ozalp.Velora.Sports.business.concretes.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //System.out.println("called: " + className + "." + methodName);
    }

    @Around("execution(* com.ozalp.Velora.Sports.business.concretes.*.*(..)) && " +
            "!execution(* com.ozalp.Velora.Sports.business.concretes.AuthManager.*(..)) && " +
            "!execution(* com.ozalp.Velora.Sports.business.concretes.*.find*(..))")
    public Object logAllServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Log log = new Log();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            log.setUser(userService.findByEmail(auth.getName()));
        }

        log.setAction(joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());

        log.setDetails(Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(", ")));

        Object result;
        try {
            result = joinPoint.proceed();
            log.setStatus(ExceptionLogStatus.SUCCESS);
            log.setNewData(result != null ? result.toString() : null);
        } catch (Exception e) {
            log.setStatus(ExceptionLogStatus.FAILED);
            log.setDetails(e.getMessage());
            throw e;
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.setExecutionTimeMs(duration);
            logRepository.save(log);
        }
        return result;
    }

}
