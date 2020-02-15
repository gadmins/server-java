package com.itfenbao.gadmins.core.interceptor;

import com.itfenbao.gadmins.core.annotation.PassToken;
import com.itfenbao.gadmins.core.exception.NotLoginException;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

public class AuthTokenInterceptor implements HandlerInterceptor {

    private final String key;
    private final String secret;

    public AuthTokenInterceptor(String key, String secret) {
        this.key = key;
        this.secret = secret;
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
        // 从 http 请求头中取出 token
        String token = request.getHeader(this.key);
        // 检查token
        if (StringUtils.isEmpty(token)) {
            throw new NotLoginException();
        }
        return true;
    }
}
