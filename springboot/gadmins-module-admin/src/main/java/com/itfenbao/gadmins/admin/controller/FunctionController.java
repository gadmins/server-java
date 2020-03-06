package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfenbao.gadmins.admin.data.dto.query.MenuQuery;
import com.itfenbao.gadmins.admin.data.vo.FunctionMenuVO;
import com.itfenbao.gadmins.admin.data.vo.FunctionPointVO;
import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.service.IFunctionConfigService;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.PageData;
import com.itfenbao.gadmins.core.web.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping(AppConfig.AdminRoute.ADMIN_FUNCTION)
@Api(tags = "系统功能点")
@Menu(value = "function", parentCode = AppConfig.SysNavMenu.BASE_MGR, title = "功能组管理", desc = "系统菜单功能配置管理", url = "/system/function")
public class FunctionController {

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
    @Function(value = "sys:function:list", sort = 0, title = "查询", desc = "功能组查询", menu = true)
    @ApiOperation("功能组分页查询")
    public JsonResult<PageData<FunctionMenuVO>> menuList(MenuQuery query) {
        Page<FunctionMenuVO> page = menuService.getListByPage(query);
        return JsonResult.success(PageData.get(page));
    }

    @GetMapping("/menu/points/{id}")
    @Function(value = "sys:function:group:list", sort = 1, title = "查看功能点", desc = "查看功能点", url = "/system/function/list")
    @ApiOperation("功能点分页查询")
    public JsonResult<PageData<FunctionPointVO>> functionPointList(@PathVariable Integer id, PageQuery query) {
        Page<FunctionPointVO> page = functionConfigService.getListByPage(query, id);
        return JsonResult.success(PageData.get(page));
    }

    @Function(value = "sys:function:group:list:edit", parentCode="sys:function:group:list", sort = 1, title = "配置", desc = "配置功能点")
    @PutMapping("/menu/point/{id}")
    @ApiOperation("功能点编辑")
    public JsonResult editPoint() {
        return JsonResult.success();
    }
//    @GetMapping("/func_id/{id}")
//    @ApiOperation("获取功能点配置")
//    public JsonResult<CamelCaseMap> getByFuncId(@PathVariable("id") Long id) {
//        LambdaQueryWrapper<FunctionConfig> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(FunctionConfig::getFuncId, id);
//        Map<String, Object> rs = functionConfigService.getMap(queryWrapper);
//        try {
//            convertToMap(rs, "common_schema", "search_schema");
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return JsonResult.success(new CamelCaseMap(rs));
//    }
//
//    private void convertToMap(Map map, String... keys) throws JsonProcessingException {
//        for (int i = 0; i < keys.length; i++) {
//            String key = keys[i];
//            if (map.containsKey(key)) {
//                Map schema = objectMapper.readValue(map.get(key).toString(), Map.class);
//                map.put(key, schema);
//            }
//        }
//    }

}
