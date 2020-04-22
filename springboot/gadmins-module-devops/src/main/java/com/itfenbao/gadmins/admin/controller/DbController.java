package com.itfenbao.gadmins.admin.controller;

import com.itfenbao.gadmins.admin.data.dto.param.db.AddTableParam;
import com.itfenbao.gadmins.admin.data.dto.param.db.UpdateTableParam;
import com.itfenbao.gadmins.admin.data.dto.query.DbQuery;
import com.itfenbao.gadmins.admin.service.IDbService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.web.result.JsonPageResult;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_DB)
@Api(tags = "系统数据表", hidden = AppConfig.HIDDEN_SYS_API)
@Menu(value = "table", parentCode = AppConfig.SysNavMenu.DEVOPS, sort = 0, icon = "database", title = "数据表管理", desc = "系统数据表管理", url = "/system/table")
public class DbController {

    @Autowired
    IDbService dbService;

    @Function(value = "sys:table:list", sort = 0, title = "查询", desc = "查询数据表", menu = true)
    @GetMapping("/table")
    @ApiOperation(value = "数据表查询")
    public JsonPageResult<Map> listTable(DbQuery query) {
        return JsonPageResult.success(dbService.listTableByPage(query));
    }

    @Function(value = "sys:table:add", sort = 1, title = "添加", desc = "添加数据表", btnGroup = Function.BtnGroup.TOOLBAR)
    @PostMapping("/table")
    @ApiOperation(value = "创建数据表")
    public JsonResult addTable(@RequestBody AddTableParam param) {
        boolean result = dbService.createTable(param);
        return result ? JsonResult.success() : JsonResult.failMessage("创建失败");
    }

    @Function(value = "sys:table:del", sort = 2, title = "查询", desc = "删除数据表")
    @DeleteMapping("/table/{name}")
    @ApiOperation(value = "删除数据表")
    public JsonResult delTable(@PathVariable("name") String[] name) {
        boolean result = true;
        for (String tName : name) {
            result = dbService.dropTable(tName);
            if (!result)
                break;
        }
        return result ? JsonResult.success() : JsonResult.failMessage("删除失败");
    }

    @Function(value = "sys:table:update", sort = 3, title = "修改", desc = "修改数据表", btnGroup = Function.BtnGroup.TOOLBAR)
    @PutMapping("/table/{name}")
    @ApiOperation(value = "修改数据表")
    public JsonResult updateTable(@PathVariable("name") String name, @RequestBody UpdateTableParam param) {
        boolean result = dbService.updateTable(name, param);
        return result ? JsonResult.success() : JsonResult.failMessage("更新失败");
    }

    @Function(value = "sys:table:column:list", sort = 4, title = "查询", desc = "查询数据表结构")
    @GetMapping("/column")
    @ApiOperation(value = "数据表结构查询")
    public JsonPageResult<Map> listColumn(DbQuery query) {
        return JsonPageResult.success(dbService.listColumnByPage(query));
    }

    @Function(value = "sys:table:data:list", sort = 5, title = "查询", desc = "查询表数据")
    @GetMapping("/data")
    @ApiOperation(value = "数据表数据")
    public JsonPageResult<Map> listTableData(DbQuery query) {
        return JsonPageResult.success(dbService.listTableDataByPage(query));
    }

}
