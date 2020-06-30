package com.itfenbao.gadmins.devops.service.impl;

import com.itfenbao.gadmins.devops.service.IDataQLService;
import lombok.extern.slf4j.Slf4j;
import net.hasor.dataql.DataQL;
import net.hasor.dataql.Query;
import net.hasor.dataql.QueryResult;
import net.hasor.dataway.config.DatawayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class DataQLService implements IDataQLService {

    @Lazy
    @Autowired
    DataQL dataQL;

    @Override
    public Map<String, Object> execuScript(String type, String script, Map<String, Object> params) {
        if ("sql".equalsIgnoreCase(type)) {
            script = DatawayUtils.evalCodeValueForSQL(script, params);
        }
        try {
            Query dataQLQuery = this.dataQL.createQuery(script);
            QueryResult queryResult = dataQLQuery.execute(params);
            return DatawayUtils.queryResultToResult(queryResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return DatawayUtils.exceptionToResult(e);
        }
    }
}
