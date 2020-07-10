package com.itfenbao.gadmins.admin.controller;

import cn.hutool.core.map.CamelCaseMap;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfenbao.gadmins.admin.data.dto.param.UpdateFunctionPointParam;
import com.itfenbao.gadmins.admin.data.dto.query.MenuQuery;
import com.itfenbao.gadmins.admin.data.vo.AuthFunctionPointVO;
import com.itfenbao.gadmins.admin.data.vo.FunctionMenuVO;
import com.itfenbao.gadmins.admin.data.vo.FunctionPointVO;
import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.FunctionConfig;
import com.itfenbao.gadmins.admin.service.IFunctionConfigService;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.annotation.MenuFunction;
import com.itfenbao.gadmins.core.annotation.Schema;
import com.itfenbao.gadmins.core.exception.NotAuthorizedException;
import com.itfenbao.gadmins.core.web.query.PageQuery;
import com.itfenbao.gadmins.core.web.result.JsonPageResult;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@RequestMapping(AppConfig.Route.Admin.FUNCTION)
@Api(tags = "系统功能点", hidden = AppConfig.HIDDEN_SYS_API)
@Menu(value = "function", parentCode = AppConfig.Menu.Nav.BASE_MGR, title = "功能组管理", desc = "系统菜单功能配置管理", url = "/system/function")
public class FunctionController {

    private final static String API_METHOD = "api_method";

    @Autowired
    IMenuService menuService;

    @Autowired
    IFunctionService functionService;

    @Autowired
    IFunctionConfigService functionConfigService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/list")
    @ApiOperation("功能组列表")
    public JsonResult<List<FunctionVO>> list() {
        return JsonResult.success(functionService.getListPidIsNull());
    }

    @GetMapping("/menu")
    @MenuFunction(value = "sys:function:list", title = "查询", desc = "查询功能组")
    @ApiOperation("功能组分页查询")
    @Schema(FunctionMenuVO.class)
    public JsonPageResult<FunctionMenuVO> menuList(MenuQuery query) {
        Page<FunctionMenuVO> page = menuService.getListByPage(query);
        return JsonPageResult.success(page);
    }

    @GetMapping("/menu/points/{id}")
    @Function(value = "sys:function:group:list", sort = 1, title = "功能点管理", desc = "功能点管理", url = "/system/function/list/:id")
    @ApiOperation("功能点分页查询")
    @Schema(FunctionMenuVO.class)
    public JsonPageResult<FunctionPointVO> functionPointList(@ApiParam(value = "功能点ID", required = true) @PathVariable Integer id, PageQuery query) {
        Page<FunctionPointVO> page = functionConfigService.getListByPage(query, id);
        return JsonPageResult.success(page);
    }

    @Function(value = "sys:function:group:list:edit", parentCode = "sys:function:group:list", sort = 1, title = "配置", desc = "配置功能点")
    @PutMapping("/menu/point/{id}")
    @ApiOperation("编辑功能点")
    public JsonResult editPoint(@ApiParam(value = "功能点ID", required = true) @PathVariable Integer id, @RequestBody UpdateFunctionPointParam param) {
        functionConfigService.update(
                Wrappers.<FunctionConfig>lambdaUpdate()
                        .set(FunctionConfig::getDataSchema, param.getSchema())
                        .eq(FunctionConfig::getFuncId, id)
        );
        return JsonResult.success();
    }

    @GetMapping("/schema/{id}")
    @ApiOperation("获取功能点配置")
    public JsonResult<CamelCaseMap> getById(@ApiParam(value = "功能点ID", required = true) @PathVariable("id") Integer id) {
        LambdaQueryWrapper<FunctionConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FunctionConfig::getFuncId, id);
        Map<String, Object> rs = functionConfigService.getMap(queryWrapper);
        if (rs == null) {
            return JsonResult.failMessage("数据不匹配，请检查");
        }
        try {
            convertToMap(rs, "data_schema");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return JsonResult.success(new CamelCaseMap(rs));
    }


    @GetMapping("/tabledata/{id}")
    @ApiOperation("获取列表Schema")
    public JsonResult tableData(@ApiParam(value = "功能点ID", required = true) @PathVariable Integer id) {
        if (!functionService.hasFunctionById(id)) {
            throw new NotAuthorizedException();
        }
        LambdaQueryWrapper<FunctionConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FunctionConfig::getFuncId, id);
        Map<String, Object> rs = functionConfigService.getMap(queryWrapper);
        if (rs == null) {
            return JsonResult.failMessage("数据不匹配，请检查");
        }
        if (rs.containsKey(API_METHOD) && !rs.get(API_METHOD).toString().equalsIgnoreCase(HttpMethod.GET.name())) {
            return JsonResult.failMessage("请确认是否查询列表Schema");
        }
        try {
            convertToMap(rs, "data_schema");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<String, Object> apiMap = new LinkedHashMap<>();
        apiMap.put("url", rs.get("api_url"));
        apiMap.put("method", rs.get("api_method"));
        Map<String, Object> schemaMap = (Map<String, Object>) rs.get("data_schema");
        Map<String, Object> mapResult = schemaMap != null ? new LinkedHashMap<>(schemaMap) : new LinkedHashMap<>();
        Map<String, Object> btnMap = new LinkedHashMap<>();
        mapResult.put("api", apiMap);
        mapResult.put("btn", btnMap);
        List<AuthFunctionPointVO> funcs = this.functionService.getListForCurrentAccountById(id);
        if (funcs != null) {
            btnMap.put("op", funcs.stream().filter(i -> i.getBtnGroup().equalsIgnoreCase(Function.BtnGroup.OP)).collect(Collectors.toList()));
            btnMap.put("toolbar", funcs.stream().filter(i -> i.getBtnGroup().equalsIgnoreCase(Function.BtnGroup.TOOLBAR)).collect(Collectors.toList()));
        }
        return JsonResult.success(mapResult);
    }

    @GetMapping("/formdata/{id}")
    @ApiOperation("获取表单Schema")
    public JsonResult formData(@ApiParam(value = "功能点ID", required = true) @PathVariable Integer id) {
        if (!functionService.hasFunctionById(id)) {
            throw new NotAuthorizedException();
        }
        LambdaQueryWrapper<FunctionConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FunctionConfig::getFuncId, id);
        Map<String, Object> rs = functionConfigService.getMap(queryWrapper);
        if (rs == null) {
            return JsonResult.failMessage("数据不匹配，请检查");
        }
        if (rs.containsKey(API_METHOD) && rs.get(API_METHOD).toString().equalsIgnoreCase(HttpMethod.GET.name())) {
            return JsonResult.failMessage("请确认是否查询表单Schema");
        }
        try {
            convertToMap(rs, "data_schema");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<String, Object> schemaMap = (Map<String, Object>) rs.get("data_schema");
        return JsonResult.success(schemaMap);
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
