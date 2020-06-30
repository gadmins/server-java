package com.itfenbao.gadmins.core.config;


import com.itfenbao.gadmins.core.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAnnotationConfig {

    @org.aspectj.lang.annotation.Around("within(@org.springframework.stereotype.Controller *) && @annotation(log)")
    public Object functionAccessCheck(final ProceedingJoinPoint pjp, Log log) throws Throwable {
        System.out.println("Log Check: Controller");
        return pjp.proceed();
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(log)")
    public Object restFunctionAccessCheck(final ProceedingJoinPoint pjp, Log log) throws Throwable {
        System.out.println("Log Check: RestController");
        return pjp.proceed();
    }

}
