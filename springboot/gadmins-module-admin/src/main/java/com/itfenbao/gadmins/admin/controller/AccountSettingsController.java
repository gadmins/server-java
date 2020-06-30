package com.itfenbao.gadmins.admin.controller;

import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.annotation.MenuFunction;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(AppConfig.AdminRoute.SETTINGS)
@Api(tags = "系统设置", hidden = AppConfig.HIDDEN_SYS_API)
@Menu(value = "accountsettings", parentCode = AppConfig.SysNavMenu.SETTINGS, sort = 7, title = "账户设置", desc = "当前账户设置", url = "/system/accountsettings")
public class AccountSettingsController {

    @GetMapping
    @MenuFunction(value = "sys:accountsettings:info", title = "查询", desc = "获取当前用户信息")
    public JsonResult info() {
        return JsonResult.success();
    }

}
