package com.itfenbao.gadmins.core.config;

import com.itfenbao.gadmins.core.annotation.Function;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RbacAnnotationConfig {

    @org.aspectj.lang.annotation.Around("within(@org.springframework.stereotype.Controller *) && @annotation(function)")
    public Object functionAccessCheck(final ProceedingJoinPoint pjp, Function function) throws Throwable {
        System.out.println("Function Access Check");
//        return JsonResult.http403();
        return pjp.proceed();
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(function)")
    public Object restFunctionAccessCheck(final ProceedingJoinPoint pjp, Function function) throws Throwable {
        System.out.println("Function Rest Access Check");
//        return JsonResult.http403();
        return pjp.proceed();
    }

}
