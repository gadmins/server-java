package com.itfenbao.gadmins.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Functions;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPoint;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPointConfig;
import com.itfenbao.gadmins.core.web.vo.menu.MenuConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = LoggerFactory.getLogger("AppListener");
    List<MenuConfig> menuConfigs = new ArrayList<>();

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 根容器为Spring容器
        if (event.getApplicationContext().getParent() == null) {
            Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(Menu.class);
            beans.values().forEach(bean -> {
                MenuConfig menuConfig = createMenuConfig(bean);
                List<FunctionPoint> points = new ArrayList<>();
                RequestMapping controllerMapping = AnnotationUtils.findAnnotation(bean.getClass(), RequestMapping.class);
                String baseRoutePath = controllerMapping.value()[0];
                Method[] methods = bean.getClass().getMethods();
                Arrays.stream(methods)
                        .filter(m -> AnnotationUtils.findAnnotation(m, Function.class) != null
                                || AnnotationUtils.findAnnotation(m, Functions.class) != null)
                        .forEach(m -> {
                            RequestMapping methodMapping = AnnotationUtils.findAnnotation(m, RequestMapping.class);
                            Function function = AnnotationUtils.findAnnotation(m, Function.class);
                            Functions functions = AnnotationUtils.findAnnotation(m, Functions.class);
                            if (function != null) {
                                points.add(createFunctionPoint(baseRoutePath, function, methodMapping, menuConfig.getUrl()));
                            } else if (functions != null) {
                                Arrays.stream(functions.value()).forEach(function1 -> {
                                    points.add(createFunctionPoint(baseRoutePath, function1, methodMapping, menuConfig.getUrl()));
                                });
                            }
                        });
                points.sort(Comparator.comparing(FunctionPoint::getSort));
                menuConfig.setFunctionPoints(points);
                menuConfigs.add(menuConfig);
            });
            try {
                log.info("MenuList:" + objectMapper.writeValueAsString(menuConfigs));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建MenuConfig
     *
     * @param bean
     * @return
     */
    private MenuConfig createMenuConfig(Object bean) {
        MenuConfig menuConfig = new MenuConfig();
        Menu menu = AnnotationUtils.findAnnotation(bean.getClass(), Menu.class);
        menuConfig.setCode(menu.value());
        menuConfig.setParentCode(menu.parentCode());
        menuConfig.setTitle(menu.title());
        menuConfig.setDesc(menu.desc());
        menuConfig.setUrl(menu.url());
        menuConfig.setIcon(menu.icon());
        menuConfig.setSort(menu.sort());
        return menuConfig;
    }

    /**
     * 创建FunctionPoint
     *
     * @param baseRoutePath
     * @param function
     * @param requestMapping
     * @return
     */
    private FunctionPoint createFunctionPoint(String baseRoutePath, Function function, RequestMapping requestMapping, String url) {
        FunctionPoint functionPoint = new FunctionPoint();
        functionPoint.setGroup(function.group());
        functionPoint.setCode(function.value());
        functionPoint.setTitle(function.title());
        functionPoint.setDesc(function.desc());
        functionPoint.setIcon(function.icon());
        functionPoint.setSort(function.sort());
        functionPoint.setMenu(function.menu());

        functionPoint.setUrl(StringUtils.isEmpty(function.url()) ? null : function.url());
        if (function.menu()) {
            functionPoint.setUrl(url);
        }
        functionPoint.setParentCode(StringUtils.isEmpty(function.parentCode()) ? null : function.parentCode());
        functionPoint.setBtnGroup(function.btnGroup());

        FunctionPointConfig pointConfig = new FunctionPointConfig();
        if (requestMapping != null) {
            pointConfig.setUrl(baseRoutePath + (requestMapping.value().length > 0 ? requestMapping.value()[0] : ""));
            pointConfig.setMethod(requestMapping.method()[0].name());
        } else {
            pointConfig.setUrl(baseRoutePath);
        }
        functionPoint.setPointConfig(pointConfig);
        return functionPoint;
    }

    public List<MenuConfig> getMenuConfigs() {
        return menuConfigs;
    }
}
