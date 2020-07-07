package com.itfenbao.gadmins.core.interceptor;

import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.PassToken;
import com.itfenbao.gadmins.core.auth.token.IAccountLockService;
import com.itfenbao.gadmins.core.auth.token.IPassTokenService;
import com.itfenbao.gadmins.core.exception.AccountLockedException;
import com.itfenbao.gadmins.core.exception.NotLoginException;
import com.itfenbao.gadmins.core.exception.TokenFailException;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

public class AuthTokenInterceptor implements HandlerInterceptor {

    private final AppConfig.TokenType tokenType;

    private IPassTokenService passTokenService;

    private IAccountLockService accountLockService;

    public AuthTokenInterceptor(AppConfig.TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public AuthTokenInterceptor setPassTokenService(IPassTokenService passTokenService) {
        this.passTokenService = passTokenService;
        return this;
    }

    public AuthTokenInterceptor setAccountLockService(IAccountLockService accountLockService) {
        this.accountLockService = accountLockService;
        return this;
    }

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if (passTokenService != null && passTokenService.passToken(request.getRequestURI(), request.getMethod())) {
            return true;
        }
        String token = TokenUtils.getToken();
        // 检查token
        if (StringUtils.isEmpty(token)) {
            throw new NotLoginException();
        }
        String uniqueId = TokenUtils.getUniqueIdFromToken(tokenType, token);
        if (StringUtils.isEmpty(uniqueId)) {
            // token失效
            TokenUtils.removeToken(token);
            throw new TokenFailException();
        }
        if (accountLockService != null && accountLockService.hasLock(Integer.parseInt(uniqueId))) {
            throw new AccountLockedException();
        }
        return true;
    }
}
