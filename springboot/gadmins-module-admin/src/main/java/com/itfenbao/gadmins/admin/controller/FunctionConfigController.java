package com.itfenbao.gadmins.admin.controller;


import cn.hutool.core.map.CamelCaseMap;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfenbao.gadmins.admin.entity.FunctionConfig;
import com.itfenbao.gadmins.admin.service.IFunctionConfigService;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.web.JsonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Slf4j
@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_FUNCTION_CONFIG)
@Api(tags = "FunctionConfig")
public class FunctionConfigController {

    @Autowired
    IFunctionConfigService functionConfigService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public JsonResult getById(@PathVariable("id") Long id) {
        return JsonResult.success(functionConfigService.getById(id));
    }

    @GetMapping("/func_id/{id}")
    public JsonResult getByFuncId(@PathVariable("id") Long id) {
        LambdaQueryWrapper<FunctionConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FunctionConfig::getFunId, id);
        Map rs = functionConfigService.getMap(queryWrapper);
        try {
            convertToMap(rs, "common_schema", "search_schema");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return JsonResult.success(new CamelCaseMap(rs));
    }

    private void convertToMap(Map map, String... keys) throws JsonProcessingException {
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            if (map.containsKey(key)) {
                Map schema = objectMapper.readValue(map.get(key).toString(), Map.class);
                map.put(key, schema);
            }
        }
    }

}
