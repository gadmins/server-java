package com.itfenbao.gadmins.devops.ws;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/7 5:08 下午
 */
@Component
public class LoggingWSInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        String token = TokenUtils.getToken(serverHttpRequest.getHeaders());
        if (StringUtils.isNotBlank(token)) {
            String uniqueId = TokenUtils.getUniqueIdFromToken(AppConfig.TokenType.ADMIN, token);
            return StringUtils.isNotBlank(uniqueId);
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
