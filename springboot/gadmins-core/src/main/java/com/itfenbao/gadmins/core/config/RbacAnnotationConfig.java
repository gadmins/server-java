package com.itfenbao.gadmins.core.config;

import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Functions;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.service.IUserAuthService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class RbacAnnotationConfig {

    @Autowired
    IUserAuthService userAuthService;

    @org.aspectj.lang.annotation.Around("within(@org.springframework.stereotype.Controller *) && @annotation(function)")
    public Object functionAccessCheck(final ProceedingJoinPoint pjp, Function function) throws Throwable {
        System.out.println("Function Access Check:" + function);
        if (userAuthService.hasAuth(function.value())) {
            return pjp.proceed();
        }
        return JsonResult.http403();
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.stereotype.Controller *) && @annotation(functions)")
    public Object functionsAccessCheck(final ProceedingJoinPoint pjp, Functions functions) throws Throwable {
        System.out.println("Function Access Check:" + functions);
        if (Arrays.stream(functions.value()).anyMatch(f -> userAuthService.hasAuth(f.value()))) {
            return pjp.proceed();
        }
        return JsonResult.http403();
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(function)")
    public Object restFunctionAccessCheck(final ProceedingJoinPoint pjp, Function function) throws Throwable {
        System.out.println("Function Rest Access Check:" + function);
        if (userAuthService.hasAuth(function.value())) {
            return pjp.proceed();
        }
        return JsonResult.http403();
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(functions)")
    public Object restFunctionsAccessCheck(final ProceedingJoinPoint pjp, Functions functions) throws Throwable {
        System.out.println("Function Rest Access Check:" + functions);
        if (Arrays.stream(functions.value()).anyMatch(f -> userAuthService.hasAuth(f.value()))) {
            return pjp.proceed();
        }
        return JsonResult.http403();
    }

}
