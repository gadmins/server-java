package com.itfenbao.gadmins.admin.service;

import com.itfenbao.gadmins.core.web.result.JsonResult;

import java.util.Map;

public interface IDataQLService {
    JsonResult<Map<String, Object>> execuScript(String type, String script, Map<String, Object> params);
}
