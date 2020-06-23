package com.itfenbao.gadmins.core.config;

import com.itfenbao.gadmins.core.annotation.RequiresRoles;
import com.itfenbao.gadmins.core.auth.role.IRoleAuthService;
import com.itfenbao.gadmins.core.exception.NotAuthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RoleAnnotaionConfig {

    @Autowired
    IRoleAuthService roleAuthService;

    @org.aspectj.lang.annotation.Around("within(@org.springframework.stereotype.Controller *) && @annotation(roles)")
    public Object roleAccessCheck(final ProceedingJoinPoint pjp, RequiresRoles roles) throws Throwable {
        return proceRoles(pjp, roles.value());
    }

    @org.aspectj.lang.annotation.Around("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(roles)")
    public Object restRoleAccessCheck(final ProceedingJoinPoint pjp, RequiresRoles roles) throws Throwable {
        return proceRoles(pjp, roles.value());
    }

    private Object proceRoles(ProceedingJoinPoint pjp, String[] roles) throws Throwable {
        if (roleAuthService.hasRole(roles)) {
            return pjp.proceed();
        }
        throw new NotAuthorizedException();
    }
}
