package com.itfenbao.gadmins.core.utils;

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
import java.sql.SQLException;

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

}
