package com.itfenbao.gadmins;

import com.itfenbao.gadmins.devops.ws.LoggingWSHandler;
import com.itfenbao.gadmins.devops.ws.LoggingWSInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author itfenbao
 * @version 1.0
 * @description: WebSocket配置
 * @date :2020/7/6 5:15 下午
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    LoggingWSHandler wsHandler;

    @Autowired
    LoggingWSInterceptor loggingWsInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler, "/ws/logging").addInterceptors(loggingWsInterceptor).setAllowedOrigins("*");
    }
}

