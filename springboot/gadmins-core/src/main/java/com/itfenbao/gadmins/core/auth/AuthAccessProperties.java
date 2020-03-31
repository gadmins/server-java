package com.itfenbao.gadmins.core.auth;


import com.itfenbao.gadmins.config.AppConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author itfenbao
 */
@ConfigurationProperties(
        prefix = "api-access"
)
public class AuthAccessProperties {

    @NestedConfigurationProperty
    private AuthProperties admin = new AuthProperties(AppConfig.Access.ADMIN_TOKEN, AuthFrom.COOKIE);
    @NestedConfigurationProperty
    private AuthProperties app = new AuthProperties(AppConfig.Access.APP_TOKEN, AuthFrom.HEADER);

    public AuthAccessProperties() {
    }

    public AuthProperties getAdmin() {
        return admin;
    }

    public void setAdmin(AuthProperties admin) {
        this.admin = admin;
    }

    public AuthProperties getApp() {
        return app;
    }

    public void setApp(AuthProperties app) {
        this.app = app;
    }
}
