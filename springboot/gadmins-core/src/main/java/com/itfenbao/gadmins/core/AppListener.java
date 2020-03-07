package com.itfenbao.gadmins.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Functions;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.annotation.Schema;
import com.itfenbao.gadmins.core.web.vo.FormSchemaVO;
import com.itfenbao.gadmins.core.web.vo.ListSchemaVO;
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
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
                            RequestInfo requestInfo = getRequestInfo(m, bean);
                            Function function = AnnotationUtils.findAnnotation(m, Function.class);
                            Functions functions = AnnotationUtils.findAnnotation(m, Functions.class);
                            if (function != null) {
                                points.add(createFunctionPoint(baseRoutePath, function, requestInfo, menuConfig.getUrl()));
                            } else if (functions != null) {
                                Arrays.stream(functions.value()).forEach(function1 -> {
                                    points.add(createFunctionPoint(baseRoutePath, function1, requestInfo, menuConfig.getUrl()));
                                });
                            }
                        });
                points.sort(Comparator.comparing(FunctionPoint::getSort));
                menuConfig.setFunctionPoints(points);
                menuConfigs.add(menuConfig);
            });
            try {
                log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(menuConfigs));
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
     * @param requestInfo
     * @return
     */
    private FunctionPoint createFunctionPoint(String baseRoutePath, Function function, RequestInfo requestInfo, String url) {
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
        pointConfig.setUrl(baseRoutePath + requestInfo.url);
        pointConfig.setMethod(requestInfo.method.name());
        pointConfig.setSchema(requestInfo.schema);
        functionPoint.setPointConfig(pointConfig);
        return functionPoint;
    }

    private RequestInfo getRequestInfo(Method method, Object bean) {
        RequestInfo info = new RequestInfo();
        GetMapping get = AnnotationUtils.findAnnotation(method, GetMapping.class);
        PostMapping post = AnnotationUtils.findAnnotation(method, PostMapping.class);
        PutMapping put = AnnotationUtils.findAnnotation(method, PutMapping.class);
        DeleteMapping delete = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
        if (get == null && post == null && put == null && delete == null) {
            RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
            if (requestMapping != null) {
                info.url = getPath(requestMapping.path());
                info.method = requestMapping.method().length > 0 ? requestMapping.method()[0] : RequestMethod.GET;
            }
        } else {
            if (get != null) {
                info.url = getPath(get.path());
                info.method = RequestMethod.GET;
            } else if (post != null) {
                info.url = getPath(post.path());
                info.method = RequestMethod.POST;
            } else if (put != null) {
                info.url = getPath(put.path());
                info.method = RequestMethod.PUT;
            } else if (delete != null) {
                info.url = getPath(delete.path());
                info.method = RequestMethod.DELETE;
            }
        }

        if (info.method == RequestMethod.GET) {
            Schema schema = AnnotationUtils.findAnnotation(method, Schema.class);
            if (schema != null) {
                ListSchemaVO schemaVO = new ListSchemaVO();
                ReflectionUtils.doWithFields(schema.value(), field -> {
                    schemaVO.addColumn(field);
                });
                try {
                    info.schema = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(schemaVO);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                log.error("no schema in " + method);
            }
        } else if (info.method == RequestMethod.POST || info.method == RequestMethod.PUT) {
            Class[] classes = method.getParameterTypes();
            Class schemaType = info.method == RequestMethod.POST ? classes[0] : classes[1];
            FormSchemaVO formSchemaVO = new FormSchemaVO();
            ReflectionUtils.doWithFields(schemaType, field -> {
                formSchemaVO.addFormItem(field);
            });
            try {
                info.schema = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(formSchemaVO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return info;
    }

    private String getPath(String[] path) {
        return path.length > 0 ? path[0] : "";
    }

    public List<MenuConfig> getMenuConfigs() {
        return menuConfigs;
    }

    private static class RequestInfo {
        public String url;
        public RequestMethod method;
        public String schema;
    }
}
