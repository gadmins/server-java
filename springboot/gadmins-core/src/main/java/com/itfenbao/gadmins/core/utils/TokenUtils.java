package com.itfenbao.gadmins.core.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.auth.AuthAccessProperties;
import com.itfenbao.gadmins.core.auth.AuthFrom;
import com.itfenbao.gadmins.core.auth.AuthProperties;
import com.itfenbao.gadmins.core.auth.token.TokenManager;
import com.itfenbao.gadmins.core.exception.NoMatchTokenTypeException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenUtils implements ApplicationContextAware {

    private static AuthAccessProperties authAccessProperties;

    private static ApplicationContext applicationContext;

    public TokenUtils(AuthAccessProperties authAccessProperties) {
        TokenUtils.authAccessProperties = authAccessProperties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TokenUtils.applicationContext = applicationContext;
    }

    public static boolean isCookie(AppConfig.TokenType tokenType) {
        AuthProperties authProperties = getAuthProperties(tokenType);
        return authProperties.getKeyFrom() == AuthFrom.COOKIE;
    }

    public static void setTokenCookie(AppConfig.TokenType tokenType, String token, HttpServletResponse response) {
        AuthProperties authProperties = getAuthProperties(tokenType);
        Cookie cookie = new Cookie(authProperties.getKey(), token);
        cookie.setPath("/");
        if (StringUtils.isNotBlank(authProperties.getDomain()) && !authProperties.getDomain().equals("*")) {
            cookie.setDomain(authProperties.getDomain());
        }
        response.addCookie(cookie);
    }

    public static String createToken(AppConfig.TokenType tokenType, String uniqueId) {
        TokenManager tokenManager = getTokenManager(tokenType);
        AuthProperties authProperties = getAuthProperties(tokenType);
        return tokenManager.createToken(uniqueId, authProperties.getSecret(), authProperties.getExpireTime());
    }

    public static void removeToken(String token) {
        TokenManager tokenManager = getTokenManager(getTokenType());
        tokenManager.removeToken(token);
    }

    public static String getToken() {
        return getToken(getTokenType());
    }

    public static String getToken(AppConfig.TokenType tokenType) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        AuthProperties authProperties = getAuthProperties(tokenType);
        String key = authProperties.getKey();
        String token = null;
        switch (authProperties.getKeyFrom()) {
            case COOKIE:
                token = getTokenFromCookie(request, key);
                break;
            case HEADER:
                token = request.getHeader(key);
                break;
            case ALL:
                token = request.getHeader(key);
                if (StringUtils.isBlank(token)) {
                    token = getTokenFromCookie(request, key);
                }
                break;
        }
        return token;
    }

    public static String getToken(HttpHeaders httpHeaders) {
        List<String> cookies = httpHeaders.get("Cookie");
        List<String[]> tokens = cookies.stream().map(s -> s.split("=")).filter(s -> "Admin-Token".equals(s[0])).collect(Collectors.toList());
        return tokens.size() > 0 ? tokens.get(0)[1] : null;
    }

    public static String getUniqueIdFromToken(AppConfig.TokenType tokenType, String token) {
        TokenManager tokenManager = getTokenManager(tokenType);
        AuthProperties authProperties = getAuthProperties(tokenType);
        return tokenManager.getUniqueIdFromToken(token, authProperties.getSecret());
    }

    public static String getUniqueIdFromToken() {
        String token = getToken();
        return getUniqueIdFromToken(getTokenType(), token);
    }

    private static AuthProperties getAuthProperties(AppConfig.TokenType tokenType) {
        AuthProperties authProperties = AppConfig.TokenType.ADMIN == tokenType ? authAccessProperties.getAdmin() : authAccessProperties.getApp();
        return authProperties;
    }

    private static TokenManager getTokenManager(AppConfig.TokenType tokenType) {
        Class<? extends TokenManager> clazz = getAuthProperties(tokenType).getTokenImplement();
        return applicationContext.getBean(clazz);
    }

    private static String getTokenFromCookie(HttpServletRequest request, String key) {
        if (request.getCookies() == null || request.getCookies().length == 0) {
            return null;
        }
        String token = null;
        List<Cookie> cookies = Arrays.stream(request.getCookies()).filter(it -> key.equals(it.getName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(cookies)) {
            token = cookies.get(0).getValue();
        }
        return token;
    }

    /**
     * 通过URI获取tokenType
     *
     * @return
     */
    private static AppConfig.TokenType getTokenType() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getRequestURI();
        if (UriPathUtils.matchURI(AppConfig.Route.Admin.ROOT, uri)) {
            return AppConfig.TokenType.ADMIN;
        } else if (UriPathUtils.matchURI(AppConfig.Route.App.ROOT, uri)) {
            return AppConfig.TokenType.APP;
        }
        throw new NoMatchTokenTypeException();
    }

}
