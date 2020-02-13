package com.itfenbao.gadmins.app.controller;


import com.itfenbao.gadmins.app.entity.Menu;
import com.itfenbao.gadmins.app.service.IMenuService;
import com.itfenbao.gadmins.common.AdminConfig;
import com.itfenbao.gadmins.common.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@RestController
@RequestMapping(AdminConfig.AdminRoute.ADMIN_MENU)
public class MenuController {

    @Autowired
    IMenuService menuService;

    @GetMapping
    public JsonResult<List<Menu>> test() {
        return JsonResult.success(menuService.list());
    }
}
