package com.itfenbao.gadmins.devops.service;

import java.util.Map;

public interface IDataQLService {
    Map<String, Object> execuScript(String type, String script, Map<String, Object> params);
}
