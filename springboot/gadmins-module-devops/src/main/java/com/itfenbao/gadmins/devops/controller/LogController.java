package com.itfenbao.gadmins.devops.controller;

import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.annotation.MenuFunction;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/7 7:51 上午
 */
@RestController
@RequestMapping("/log")
@Menu(value = "log", parentCode = AppConfig.SysNavMenu.DEVOPS, sort = 2, icon = "file", title = "日志查看", desc = "日志查看", url = "/system/log")
public class LogController {

    @GetMapping("/")
    @MenuFunction(value = "sys:log:list", title = "查看", desc = "查看")
    public JsonResult list() {
        return JsonResult.success();
    }
}
