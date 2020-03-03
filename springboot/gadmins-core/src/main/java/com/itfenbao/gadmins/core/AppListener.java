package com.itfenbao.gadmins.core;

import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 根容器为Spring容器
        if (event.getApplicationContext().getParent() == null) {
            Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(Menu.class);
            beans.values().forEach(bean -> {
                Menu menu = AnnotationUtils.findAnnotation(bean.getClass(), Menu.class);
                RequestMapping controllerMapping = AnnotationUtils.findAnnotation(bean.getClass(), RequestMapping.class);
                System.out.println("bean:" + bean + ",menu:" + menu + ",url:" + controllerMapping.value()[0]);
                Method[] methods = bean.getClass().getMethods();
                Arrays.stream(methods).filter(m -> AnnotationUtils.findAnnotation(m, Function.class) != null).forEach(m -> {
                    Function function = AnnotationUtils.findAnnotation(m, Function.class);
                    RequestMapping requestMapping = AnnotationUtils.findAnnotation(m, RequestMapping.class);
                    System.out.println("method:" + m.getName() + ",fun:" + function + ",mapping:" + requestMapping.method()[0].name());
                });
            });
        }
    }

}
