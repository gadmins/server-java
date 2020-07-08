package com.itfenbao.gadmins.devops.controller;

import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.annotation.MenuFunction;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConfig.Route.Admin.TOOL)
@Api(tags = "系统代码工具", hidden = AppConfig.HIDDEN_SYS_API)
@Menu(value = "tool", parentCode = AppConfig.Menu.Nav.DEVOPS, sort = 3, icon = "tool", title = "代码工具", desc = "代码工具", url = "/system/tool")
public class ToolController {

    @MenuFunction(value = "sys:tool:list", title = "查询", desc = "查询代码工具")
    @GetMapping()
    @ApiOperation(value = "代码工具列表")
    public JsonResult listTable() {
        return JsonResult.success();
    }
}
