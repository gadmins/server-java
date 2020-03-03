package com.itfenbao.gadmins.core.config;

import com.itfenbao.gadmins.core.annotation.Dict;
import com.itfenbao.gadmins.core.web.service.IDictManager;
import com.itfenbao.gadmins.core.web.vo.DictVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class DictAnnotationBeanPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger("DictAnnotationBeanPostProcessor");

    List<DictFieldBean> dictFieldBeans = new ArrayList<>();

    @Autowired
    IDictManager dictManager;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), (f) -> {
            Dict dict = f.getAnnotation(Dict.class);
            if (dict == null) return;
            if (f.getType() == java.util.List.class) {
                Type genericType = f.getGenericType();
                if (isDictVO(genericType)) {
                    dictFieldBeans.add(new DictFieldBean(bean, f, dict.value()));
                    setValue(bean, f, dict.value());
                } else {
                    logError(f);
                }
            } else {
                logError(f);
            }
        });
        return bean;
    }

    public void refresh() {
        dictFieldBeans.forEach(db -> {
            setValue(db.bean, db.field, db.code);
        });
    }

    private boolean isDictVO(Type genericType) {
        if (genericType != null && genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            //得到泛型里的class类型对象
            Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
            if (genericClazz == DictVO.class) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void setValue(Object bean, Field f, String code) {
        if (dictManager == null) {
            log.error("IDictManager not implements.");
        }
        ReflectionUtils.makeAccessible(f);
        ReflectionUtils.setField(f, bean, dictManager.getDictsByCode(code));
    }

    private void logError(Field f) {
        log.error("[" + f + "]必须使用List<DictVO>类型");
    }


    private static class DictFieldBean {
        Object bean;
        Field field;
        String code;

        public DictFieldBean(Object bean, Field field, String code) {
            this.bean = bean;
            this.field = field;
            this.code = code;
        }
    }
}
