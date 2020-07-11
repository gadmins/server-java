package com.itfenbao.gadmins.devops.controller;

import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.annotation.MenuFunction;
import com.itfenbao.gadmins.core.web.data.dto.param.db.*;
import com.itfenbao.gadmins.core.web.data.dto.query.db.DbTableQuery;
import com.itfenbao.gadmins.core.web.data.dto.query.db.TableColumnQuery;
import com.itfenbao.gadmins.core.web.data.dto.query.db.TableDataQuery;
import com.itfenbao.gadmins.core.web.result.JsonPageResult;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import com.itfenbao.gadmins.core.web.service.IDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(AppConfig.Route.Admin.DB)
@Api(tags = "系统数据表", hidden = AppConfig.HIDDEN_SYS_API)
@Menu(value = "table", parentCode = AppConfig.Menu.Nav.DEVOPS, icon = "database", title = "数据表管理", desc = "系统数据表管理", url = "/system/table")
public class DbController {

    @Autowired
    IDbService dbService;

    @MenuFunction(value = "sys:table:list", title = "查询", desc = "查询数据表")
    @GetMapping("/table")
    @ApiOperation(value = "查询数据表列表")
    public JsonPageResult<Map> listTable(DbTableQuery query) {
        return JsonPageResult.success(dbService.listTableByPage(query));
    }

    @Function(value = "sys:table:add", sort = 1, title = "添加", desc = "添加数据表", btnGroup = Function.BtnGroup.TOOLBAR)
    @PostMapping("/table")
    @ApiOperation(value = "创建数据表")
    public JsonResult addTable(@RequestBody AddTableParam param) {
        boolean result = dbService.createTable(param);
        return result ? JsonResult.success() : JsonResult.failMessage("已存在，创建失败");
    }

    @Function(value = "sys:table:del", sort = 2, title = "删除", desc = "删除数据表")
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

    @Function(value = "sys:table:column:list", sort = 4, title = "表结构管理", desc = "表结构管理", url = "/system/table/struct/:name")
    @GetMapping("/column")
    @ApiOperation(value = "数据表结构查询")
    public JsonPageResult<Map> listColumn(TableColumnQuery query) {
        return JsonPageResult.success(dbService.listColumnByPage(query));
    }

    @Function(value = "sys:table:column:add", parentCode = "sys:table:column:list", sort = 5, title = "添加", desc = "添加表结构字段")
    @PostMapping("/column")
    @ApiOperation(value = "添加数据表字段")
    public JsonResult addColumn(@RequestBody AddColumnParam param) {
        return dbService.addColumn(param) ? JsonResult.success() : JsonResult.failMessage("添加失败");
    }

    @Function(value = "sys:table:column:del", parentCode = "sys:table:column:list", sort = 6, title = "删除", desc = "删除表结构字段")
    @DeleteMapping("/column")
    @ApiOperation(value = "删除数据表字段")
    public JsonResult deleteColumn(DeleteColumnParam param) {
        return dbService.deleteColumn(param) ? JsonResult.success() : JsonResult.failMessage("删除失败");
    }

    @Function(value = "sys:table:column:update", parentCode = "sys:table:column:list", sort = 7, title = "修改", desc = "修改表结构字段")
    @PutMapping("/column")
    @ApiOperation(value = "修改数据表字段")
    public JsonResult updateColumn(@RequestBody UpdateColumnParam param) {
        return dbService.updateColumn(param) ? JsonResult.success() : JsonResult.failMessage("修改失败");
    }

    @Function(value = "sys:table:data:list", sort = 5, title = "查询数据", desc = "查询表数据", url = "/system/table/data/:name")
    @GetMapping("/data")
    @ApiOperation(value = "数据表数据")
    public JsonPageResult<Map> listTableData(TableDataQuery query) {
        return JsonPageResult.success(dbService.listTableDataByPage(query));
    }

}
