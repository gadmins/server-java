package com.itfenbao.gadmins.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

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

}
