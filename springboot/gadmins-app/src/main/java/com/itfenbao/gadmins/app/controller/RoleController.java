package com.itfenbao.gadmins.app.controller;


import com.itfenbao.gadmins.app.entity.Role;
import com.itfenbao.gadmins.app.service.IRoleService;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.web.PageData;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@Api(tags = "Role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @ApiOperation(value = "获取所有非超管角色")
    @GetMapping("/all")
    public JsonResult<List<Role>> allList() {
        return JsonResult.success(roleService.getAllRoleNotSuperAdmin());
    }

    @ApiOperation(value = "分页查询非超管角色")
    @GetMapping()
    public JsonResult<PageData<Role>> list(PageQuery query) {
        PageData<Role> page = PageData.get(roleService.getPageListNotSuperAdmin(query));
        return JsonResult.success(page);
    }
}
