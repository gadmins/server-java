package com.itfenbao.gadmins.admin.controller;

import com.itfenbao.gadmins.admin.service.InitService;
import com.itfenbao.gadmins.core.annotation.PassToken;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date 2020/7/14 8:51 下午
 */
@RestController
@RequestMapping("/system")
public class InitController {

    InitService initService;

    @Autowired
    public void setInitService(InitService initService) {
        this.initService = initService;
    }

    @GetMapping("/init")
    @PassToken
    public JsonResult init() throws SQLException {
        return JsonResult.success(this.initService.init() ? "系统初始化成功" : "系统已经初始化");
    }

    @GetMapping("/init/check")
    @PassToken
    public JsonResult check() {
        return this.initService.isInit() ? JsonResult.success("系统已经初始化") : JsonResult.fail();
    }
}
