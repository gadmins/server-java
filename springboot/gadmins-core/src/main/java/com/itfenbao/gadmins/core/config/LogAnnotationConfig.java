package com.itfenbao.gadmins.core.config;


import com.itfenbao.gadmins.core.annotation.Log;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import com.itfenbao.gadmins.core.web.service.ILogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAnnotationConfig {

    ILogService logService;

    @Autowired(required = false)
    public void setLogService(ILogService logService) {
        this.logService = logService;
    }

    @Pointcut("@annotation(com.itfenbao.gadmins.core.annotation.Log)")
    public void logPointcut() {
    }

    @Pointcut("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void exceptionLogPoinCut() {
    }

    @AfterReturning(value = "logPointcut()", returning = "result")
    public void saveLog(final JoinPoint pjp, Object result) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            handler(log, (JsonResult) result);
        }
    }

    @AfterThrowing(pointcut = "exceptionLogPoinCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint pjp, Throwable e) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            handler(log, e);
        }
    }


    private void handler(Log log, JsonResult rs) {
        if (logService != null) {
            String logStr = String.format("%s - %s", log.value(), rs.getMsg());
            logService.save(logStr, log.tags());
        }
    }

    private void handler(Log log, Throwable throwable) {
        if (logService != null) {
            String logStr = String.format("%s - %s", log.value(), throwable.getMessage());
            logService.save(logStr, log.tags());
        }
    }

}
