package com.itfenbao.gadmins.admin.service.impl;

import com.itfenbao.gadmins.admin.service.IDataQLService;
import net.hasor.dataql.DataQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DataQLService implements IDataQLService {

    @Lazy
    @Autowired
    DataQL dataQL;

}
