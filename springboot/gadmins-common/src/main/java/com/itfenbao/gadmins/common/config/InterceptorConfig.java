package com.itfenbao.gadmins.common.config;

import com.itfenbao.gadmins.common.AppConfig;
import com.itfenbao.gadmins.common.auth.AuthAccessProperties;
import com.itfenbao.gadmins.common.auth.AuthProperties;
import com.itfenbao.gadmins.common.interceptor.AuthTokenInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties({AuthAccessProperties.class})
public class InterceptorConfig implements WebMvcConfigurer {

    private final AuthAccessProperties properties;

    public InterceptorConfig(AuthAccessProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AuthProperties admin = this.properties.getAdmin();
        AuthProperties app = this.properties.getApp();
        if (admin.isOpen()) {
            registry.addInterceptor(new AuthTokenInterceptor(admin.getKey(), admin.getSecret())).addPathPatterns(AppConfig.AdminRoute.ADMIN + "/**");
        }
        if (app.isOpen()) {
            registry.addInterceptor(new AuthTokenInterceptor(app.getKey(), app.getSecret())).addPathPatterns(AppConfig.AppRoute.APP + "/**");
        }
    }

}
