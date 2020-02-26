package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.admin.data.dto.param.role.AddRoleParam;
import com.itfenbao.gadmins.admin.data.dto.param.role.UpdateRoleParam;
import com.itfenbao.gadmins.admin.data.vo.RoleMenuVO;
import com.itfenbao.gadmins.admin.entity.*;
import com.itfenbao.gadmins.admin.service.*;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.PageData;
import com.itfenbao.gadmins.core.web.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_ROLE)
@Api(tags = "系统角色")
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
        if(!CollectionUtils.isEmpty(menuIds)) {
            keys.addAll(menuService.list(Wrappers.<Menu>lambdaQuery().in(Menu::getId, menuIds)).stream().map(it -> it.getMCode()).collect(Collectors.toList()));
        }
        if(!CollectionUtils.isEmpty(funcIds)) {
            keys.addAll(functionService.list(Wrappers.<com.itfenbao.gadmins.admin.entity.Function>lambdaQuery().in(Function::getId, funcIds)).stream().map(it -> it.getFuncCode()).collect(Collectors.toList()));
        }
        roleMenuVO.setKeys(keys);
        return JsonResult.success(roleMenuVO);
    }


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
