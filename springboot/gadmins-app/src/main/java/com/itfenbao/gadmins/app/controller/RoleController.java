package com.itfenbao.gadmins.app.controller;


import com.itfenbao.gadmins.app.service.IRoleService;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_ROLE)
public class RoleController {

    @Autowired
    IRoleService roleService;

    @GetMapping()
    public JsonResult list() {
        return JsonResult.success(roleService.getListNotSuperAdmin());
    }
}
