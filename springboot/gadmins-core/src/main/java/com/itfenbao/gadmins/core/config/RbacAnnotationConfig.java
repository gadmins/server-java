package com.itfenbao.gadmins.core.config;

import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Functions;
import com.itfenbao.gadmins.core.annotation.PassToken;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import com.itfenbao.gadmins.core.web.service.IUserAuthService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;


@Aspect
@Component
public class RbacAnnotationConfig {

    @Autowired
    IUserAuthService userAuthService;

    @org.aspectj.lang.annotation.Around("within(@org.springframework.stereotype.Controller *) && @annotation(function)")
    public Object functionAccessCheck(final ProceedingJoinPoint pjp, Function function) throws Throwable {
        System.out.println("Function Access Check:" + function);
        return proceFunction(pjp, function);
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.stereotype.Controller *) && @annotation(functions)")
    public Object functionsAccessCheck(final ProceedingJoinPoint pjp, Functions functions) throws Throwable {
        System.out.println("Function Access Check:" + functions);
        return proceFunctions(pjp, functions);
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(function)")
    public Object restFunctionAccessCheck(final ProceedingJoinPoint pjp, Function function) throws Throwable {
        System.out.println("Function Rest Access Check:" + function);
        return proceFunction(pjp, function);
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(functions)")
    public Object restFunctionsAccessCheck(final ProceedingJoinPoint pjp, Functions functions) throws Throwable {
        System.out.println("Function Rest Access Check:" + functions);
        return proceFunctions(pjp, functions);
    }

    private Object proceFunction(ProceedingJoinPoint pjp, Function function) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return pjp.proceed();
            }
        }
        if (userAuthService.hasAuth(function.value())) {
            return pjp.proceed();
        }
        return JsonResult.http403();
    }

    private Object proceFunctions(ProceedingJoinPoint pjp, Functions functions) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return pjp.proceed();
            }
        }
        if (Arrays.stream(functions.value()).anyMatch(f -> userAuthService.hasAuth(f.value()))) {
            return pjp.proceed();
        }
        return JsonResult.http403();
    }

}
