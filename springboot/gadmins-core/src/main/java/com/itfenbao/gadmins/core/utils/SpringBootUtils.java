package com.itfenbao.gadmins.core.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.itfenbao.gadmins.core.web.service.IMenuScanService;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;

/**
 * TODO
 *
 * @author itfenbao
 * @version 1.0
 * @date 2020/4/3 7:52 下午
 */
@Component
public class SpringBootUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBootUtils.context = applicationContext;
    }

    /**
     * 获取当前环境
     *
     * @return
     */
    public final static String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }

    public final static String getBaseUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            URL url = new URL(request.getHeader("Referer")); // request.getRequestURL().toString()
            String protocol = url.getProtocol();
            String host = url.getHost();
            String contextPath = getContextPath();
            int port = url.getPort();
            StringBuilder sb = new StringBuilder(protocol + "://" + host);
            if (port != 80 && port > 0) {
                sb.append(":" + port);
            }
            if (StringUtils.isNotBlank(contextPath)) {
                sb.append("/" + contextPath);
            }
            return sb.toString();
        } catch (MalformedURLException e) {
        }
        return null;
    }

    public final static String getContextPath() {
        ServerProperties serverProperties = context.getBean(ServerProperties.class);
        String contextPath = serverProperties.getServlet().getContextPath();
        if (contextPath == null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                contextPath = request.getContextPath();
            } else {
                contextPath = "";
            }
        }
        return contextPath;
    }

    /**
     * 执行sql脚本
     *
     * @param datasource
     * @param path
     * @throws SQLException
     */
    public static void executeSqlScript(DataSource datasource, String path) throws SQLException {
        Resource resource = context.getResource(path);
        ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
    }

    /**
     * 获取IMenuScanService的所有实现
     *
     * @return
     */
    public static Map<String, IMenuScanService> getMenuScanServices() {
        return context.getBeansOfType(IMenuScanService.class);
    }

}
