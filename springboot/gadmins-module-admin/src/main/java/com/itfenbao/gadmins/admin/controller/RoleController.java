package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.admin.data.dto.param.role.AddRoleParam;
import com.itfenbao.gadmins.admin.data.dto.param.role.UpdateRoleParam;
import com.itfenbao.gadmins.admin.data.vo.RoleMenuVO;
import com.itfenbao.gadmins.admin.entity.*;
import com.itfenbao.gadmins.admin.service.*;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.PageData;
import com.itfenbao.gadmins.core.web.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Slf4j
@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_ROLE)
@Api(tags = "系统角色")
@com.itfenbao.gadmins.core.annotation.Menu(value = "sys.role", title = "角色管理", desc = "系统角色管理", url = "/system/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @Autowired
    IRlMenuRoleService menuRoleService;

    @Autowired
    IRlFunctionRoleService functionRoleService;

    @Autowired
    IMenuService menuService;

    @Autowired
    IFunctionService functionService;

    @com.itfenbao.gadmins.core.annotation.Function(
            value = "sys:role:list", sort = 0,
            title = "角色查询", menu = true
    )
    @GetMapping()
    @ApiOperation(value = "分页查询非超管角色")
    public JsonResult<PageData<Role>> list(PageQuery query) {
        PageData<Role> page = PageData.get(roleService.getPageListNotSuperAdmin(query));
        return JsonResult.success(page);
    }

    @com.itfenbao.gadmins.core.annotation.Function(
            value = "sys:role:add", sort = 1,
            title = "新增", desc = "新增角色", icon = "plus",
            btnGroup = com.itfenbao.gadmins.core.annotation.Function.BtnGroup.TOOLBAR
    )
    @PostMapping()
    public JsonResult create(@RequestBody AddRoleParam param) {
        Role role = new Role();
        role.setName(param.getName());
        role.setRCode(param.getRcode());
        role.setRDesc(param.getRdesc());
        roleService.save(role);

        List<RlMenuRole> menuRoles = param.getMenuIds().stream().map(menuId -> {
            RlMenuRole menuRole = new RlMenuRole();
            menuRole.setRoleId(role.getId());
            menuRole.setMenuId(menuId);
            return menuRole;
        }).collect(Collectors.toList());
        menuRoleService.saveBatch(menuRoles);

        List<RlFunctionRole> functionRoles = param.getFuncIds().stream().map(funcId -> {
            RlFunctionRole functionRole = new RlFunctionRole();
            functionRole.setRoleId(role.getId());
            functionRole.setFuncId(funcId);
            return functionRole;
        }).collect(Collectors.toList());
        functionRoleService.saveBatch(functionRoles);

        return JsonResult.success();
    }

    @com.itfenbao.gadmins.core.annotation.Function(
            value = "sys:role:edit", sort = 2,
            title = "编辑", desc = "编辑角色"
    )
    @PutMapping("/{id}")
    public JsonResult update(@PathVariable("id") Integer id, @RequestBody UpdateRoleParam param) {
        Role role = new Role();
        role.setId(id);
        role.setName(param.getName());
        role.setRCode(param.getRcode());
        role.setRDesc(param.getRdesc());
        roleService.updateById(role);

        menuRoleService.remove(Wrappers.<RlMenuRole>lambdaQuery().eq(RlMenuRole::getRoleId, id));
        functionRoleService.remove(Wrappers.<RlFunctionRole>lambdaQuery().eq(RlFunctionRole::getRoleId, id));

        List<RlMenuRole> menuRoles = param.getMenuIds().stream().map(menuId -> {
            RlMenuRole menuRole = new RlMenuRole();
            menuRole.setRoleId(role.getId());
            menuRole.setMenuId(menuId);
            return menuRole;
        }).collect(Collectors.toList());
        menuRoleService.saveBatch(menuRoles);

        List<RlFunctionRole> functionRoles = param.getFuncIds().stream().map(funcId -> {
            RlFunctionRole functionRole = new RlFunctionRole();
            functionRole.setRoleId(role.getId());
            functionRole.setFuncId(funcId);
            return functionRole;
        }).collect(Collectors.toList());
        functionRoleService.saveBatch(functionRoles);

        return JsonResult.success();
    }

    @com.itfenbao.gadmins.core.annotation.Function(
            value = "sys:role:copy", sort = 3,
            title = "复制", desc = "复制角色", icon = "plus"
    )
    public void copy() {
    }

    @com.itfenbao.gadmins.core.annotation.Function(
            value = "sys:role:del", sort = 4,
            title = "批量删除", desc = "删除账户",
            btnGroup = com.itfenbao.gadmins.core.annotation.Function.BtnGroup.TOOLBAR
    )
    @DeleteMapping("/{ids}")
    public JsonResult deletes(@PathVariable() List<Integer> ids) {
        roleService.removeByIds(ids);
        return JsonResult.success();
    }

    @GetMapping("/{id}/menucodes")
    public JsonResult<RoleMenuVO> menucodes(@PathVariable("id") Integer id) {
        List<RlMenuRole> menus = menuRoleService.list(Wrappers.<RlMenuRole>lambdaQuery().eq(RlMenuRole::getRoleId, id));
        List<RlFunctionRole> functions = functionRoleService.list(Wrappers.<RlFunctionRole>lambdaQuery().eq(RlFunctionRole::getRoleId, id));
        RoleMenuVO roleMenuVO = new RoleMenuVO();
        List<Integer> menuIds = menus.stream().map(func -> func.getMenuId()).collect(Collectors.toList());
        List<Integer> funcIds = functions.stream().map(func -> func.getFuncId()).collect(Collectors.toList());
        roleMenuVO.setMenuIds(menuIds);
        roleMenuVO.setFuncIds(funcIds);
        List<String> keys = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuIds)) {
            keys.addAll(menuService.list(Wrappers.<Menu>lambdaQuery().in(Menu::getId, menuIds)).stream().map(it -> it.getMCode()).collect(Collectors.toList()));
        }
        if (!CollectionUtils.isEmpty(funcIds)) {
            keys.addAll(functionService.list(Wrappers.<com.itfenbao.gadmins.admin.entity.Function>lambdaQuery().in(Function::getId, funcIds)).stream().map(it -> it.getFuncCode()).collect(Collectors.toList()));
        }
        roleMenuVO.setKeys(keys);
        return JsonResult.success(roleMenuVO);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有非超管角色")
    public JsonResult<List<Role>> allList() {
        return JsonResult.success(roleService.getAllRoleNotSuperAdmin());
    }

}
