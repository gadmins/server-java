package com.itfenbao.gadmins.core;

import com.itfenbao.gadmins.core.config.DictAnnotationBeanPostProcessor;
import com.itfenbao.gadmins.core.event.RefreshDictEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DictRefreshListener implements ApplicationListener<RefreshDictEvent> {

    @Autowired
    DictAnnotationBeanPostProcessor dictAnnotationBeanPostProcessor;

    @Override
    public void onApplicationEvent(RefreshDictEvent event) {
        dictAnnotationBeanPostProcessor.refresh();
    }
}
