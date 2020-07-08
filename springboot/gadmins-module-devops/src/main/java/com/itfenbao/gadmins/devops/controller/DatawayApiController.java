package com.itfenbao.gadmins.devops.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.utils.SpringBootUtils;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import com.itfenbao.gadmins.core.web.result.JsonReturnCode;
import com.itfenbao.gadmins.core.web.service.IUserAuthService;
import com.itfenbao.gadmins.devops.entity.DatawayApi;
import com.itfenbao.gadmins.devops.service.IApiService;
import com.itfenbao.gadmins.devops.service.IDataQLService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * dataway api 匹配控制器
 *
 * @author itfenbao
 */
@Slf4j
@ApiIgnore
@RestController
@RequestMapping
public class DatawayApiController {

    @Autowired
    IApiService apiService;

    @Autowired
    IDataQLService dataQLService;

    @Autowired
    IUserAuthService userAuthService;

    @GetMapping(value = {
            AppConfig.Route.Admin.ALL,
            AppConfig.Route.App.ALL
    })
    public JsonResult execApiForGet(Map<String, Object> params, HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        DatawayApi api = this.matchDataWayApi(request, antPathMatcher);
        if (api == null) {
            return JsonResult.http404();
        }
        return execScript(request, antPathMatcher, api, params);
    }

    @RequestMapping(value = {
            AppConfig.Route.Admin.ALL,
            AppConfig.Route.App.ALL
    }, method = {
            RequestMethod.POST,
            RequestMethod.PUT,
    })
    public JsonResult execApiForPostOrPut(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        DatawayApi api = this.matchDataWayApi(request, antPathMatcher);
        if (api == null) {
            return JsonResult.http404();
        }
        return execScript(request, antPathMatcher, api, params);
    }

    @DeleteMapping(value = {
            AppConfig.Route.Admin.ALL,
            AppConfig.Route.App.ALL
    })
    public JsonResult execApiForDelete(HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        DatawayApi api = this.matchDataWayApi(request, antPathMatcher);
        if (api == null) {
            return JsonResult.http404();
        }
        return execScript(request, antPathMatcher, api, null);
    }

    /**
     * 执行脚本
     *
     * @param request
     * @param antPathMatcher
     * @param api
     * @param params
     * @return
     */
    private JsonResult execScript(HttpServletRequest request, AntPathMatcher antPathMatcher, DatawayApi api, Map<String, Object> params) {
        String contextPath = SpringBootUtils.getContextPath();
        String uri = request.getRequestURI();
        // 获取PathVars
        Map<String, String> pathVars = antPathMatcher.extractUriTemplateVariables(contextPath + api.getApiPath(), uri);
        if (!CollectionUtils.isEmpty(pathVars)) {
            pathVars.forEach((k, v) -> {
                params.put(k, v);
            });
        }
        Map<String, Object> ret = dataQLService.execuScript(api.getScriptType(), api.getApiScript(), params);
        int code = MapUtil.getInt(ret, "code");
        if (code == JsonReturnCode.SUCCESS.getCode()) {
            return JsonResult.success(ret.get("data"));
        } else {
            return JsonResult.fail(MapUtil.getStr(ret, "message"));
        }
    }

    /**
     * 匹配DatawayApi
     *
     * @param request
     * @param antPathMatcher
     * @return
     */
    private DatawayApi matchDataWayApi(HttpServletRequest request, AntPathMatcher antPathMatcher) {
        String contextPath = SpringBootUtils.getContextPath();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        List<DatawayApi> apiList = apiService.list(Wrappers.<DatawayApi>lambdaQuery().eq(DatawayApi::getApiMethod, method));
        if (CollectionUtils.isEmpty(apiList)) {
            log.warn("[" + method + "] " + uri + " not found.");
            return null;
        }
        apiList = apiList.stream().filter(it -> {
            String path = contextPath + it.getApiPath();
            return path.equals(uri) || antPathMatcher.match(path, uri);
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(apiList)) {
            log.warn("[" + method + "] " + uri + " not found.");
            return null;
        }
        if (apiList.get(0).getStatus() == 1) {
            return apiList.get(0);
        }
        return null;
    }

}
