package com.itfenbao.gadmins.core.config;

import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.auth.AuthAccessProperties;
import com.itfenbao.gadmins.core.auth.AuthProperties;
import com.itfenbao.gadmins.core.auth.token.IAccountLockService;
import com.itfenbao.gadmins.core.auth.token.IPassTokenService;
import com.itfenbao.gadmins.core.interceptor.AuthTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties({AuthAccessProperties.class})
public class AuthInterceptorConfig implements WebMvcConfigurer {

    private final AuthAccessProperties properties;

    private IPassTokenService passTokenService;

    private IAccountLockService accountLockService;

    @Autowired(required = false)
    public void setPassTokenService(IPassTokenService passTokenService) {
        this.passTokenService = passTokenService;
    }

    @Autowired(required = false)
    public void setAccountLockService(IAccountLockService accountLockService) {
        this.accountLockService = accountLockService;
    }

    public AuthInterceptorConfig(AuthAccessProperties properties) {
        this.properties = properties;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AuthProperties admin = this.properties.getAdmin();
        AuthProperties app = this.properties.getApp();
        if (admin.isOpen()) {
            registry.addInterceptor(
                    new AuthTokenInterceptor(AppConfig.TokenType.ADMIN)
                            .setPassTokenService(passTokenService)
                            .setAccountLockService(accountLockService)
            ).addPathPatterns(AppConfig.AdminRoute.ALL);
        }
        if (app.isOpen()) {
            registry.addInterceptor(
                    new AuthTokenInterceptor(AppConfig.TokenType.APP)
                            .setPassTokenService(passTokenService)
            ).addPathPatterns(AppConfig.AppRoute.ALL);
        }
    }

}
