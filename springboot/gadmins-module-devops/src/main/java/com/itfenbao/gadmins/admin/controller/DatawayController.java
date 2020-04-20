package com.itfenbao.gadmins.admin.controller;

import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_DATAWAY)
@Api(tags = "系统动态接口")
@Menu(value = "dataway", parentCode = AppConfig.SysNavMenu.DEVOPS, sort = 10, icon = "cloud", title = "动态接口管理", desc = "动态接口管理", url = "/system/dataway")
public class DatawayController {

    @Function(value = "sys:dataway:list", sort = 0, title = "查询", desc = "查询动态接口", menu = true)
    @GetMapping()
    @ApiOperation(value = "数据表查询")
    public JsonResult listTable() {
        return JsonResult.success();
    }
}
