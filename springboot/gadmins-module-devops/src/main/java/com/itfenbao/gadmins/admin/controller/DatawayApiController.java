package com.itfenbao.gadmins.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.admin.entity.DatawayApi;
import com.itfenbao.gadmins.admin.service.IApiService;
import com.itfenbao.gadmins.admin.service.IDataQLService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import com.itfenbao.gadmins.core.web.result.JsonReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class DatawayApiController {

    @Autowired
    IApiService apiService;

    @Autowired
    IDataQLService dataQLService;

    @RequestMapping(path = {
            AppConfig.AdminRoute.ADMIN + "/**/*"
    }, method = {
            RequestMethod.GET,
            RequestMethod.POST,
            RequestMethod.PUT,
            RequestMethod.DELETE
    })
    public JsonResult api(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        DatawayApi api = apiService.getOne(Wrappers.<DatawayApi>lambdaQuery().eq(DatawayApi::getApiPath, uri).eq(DatawayApi::getApiMethod, method));
        if (api == null) {
            log.warn("[" + method + "] " + uri + " not found.");
            return JsonResult.http404();
        }
        Map<String, Object> params = null;
        if (HttpMethod.GET.matches(method)) {
            log.info("GET queryString=" + request.getQueryString());
        }
        // TODO: 不同方法的处理
        Map<String, Object> ret = dataQLService.execuScript(api.getScriptType(), api.getApiScript(), params);
        int code = MapUtil.getInt(ret, "code");
        if (code == JsonReturnCode.SUCCESS.getCode()) {
            return JsonResult.success(ret.get("data"));
        } else {
            return JsonResult.fail(MapUtil.getStr(ret, "message"));
        }
    }

}
