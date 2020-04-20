package com.itfenbao.gadmins.admin.controller;

import com.itfenbao.gadmins.admin.data.dto.query.DbQuery;
import com.itfenbao.gadmins.admin.service.IDbService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.web.result.JsonPageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_DB)
@Api(tags = "系统数据表")
@Menu(value = "table", parentCode = AppConfig.SysNavMenu.DEVOPS, sort = 10, icon = "database", title = "数据表管理", desc = "系统数据表管理", url = "/system/table")
public class DbController {

    @Autowired
    IDbService dbService;

    @Function(value = "sys:table:list", sort = 0, title = "查询", desc = "查询数据表", menu = true)
    @GetMapping()
    @ApiOperation(value = "数据表查询")
    public JsonPageResult<Map> list(DbQuery query) {
        return JsonPageResult.success(dbService.listTableByPage(query));
    }

    @Function(value = "sys:table:column:list", sort = 0, title = "查询", desc = "查询数据表结构")
    @GetMapping("/column")
    @ApiOperation(value = "数据表结构查询")
    public JsonPageResult<Map> listColumn(DbQuery query) {
        return JsonPageResult.success(dbService.listColumnByPage(query));
    }

    @Function(value = "sys:table:data:list", sort = 0, title = "查询", desc = "查询表数据")
    @GetMapping("/data")
    @ApiOperation(value = "数据表数据")
    public JsonPageResult<Map> listTableData(DbQuery query) {
        return JsonPageResult.success(dbService.listTableDataByPage(query));
    }

}
