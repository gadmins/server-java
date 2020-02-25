package com.itfenbao.gadmins.admin.controller;


import com.itfenbao.gadmins.admin.data.dto.param.role.AddRoleParam;
import com.itfenbao.gadmins.admin.data.dto.param.role.UpdateRoleParam;
import com.itfenbao.gadmins.admin.entity.Role;
import com.itfenbao.gadmins.admin.service.IRoleService;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.PageData;
import com.itfenbao.gadmins.core.web.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "系统角色")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @PostMapping()
    public JsonResult create(@RequestBody AddRoleParam param) {
        Role role = new Role();
        role.setName(param.getName());
        role.setRCode(param.getRcode());
        role.setRDesc(param.getRdesc());
        roleService.save(role);
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
        return JsonResult.success();
    }

    @DeleteMapping("/{ids}")
    public JsonResult deletes(@PathVariable() List<Integer> ids) {
        roleService.removeByIds(ids);
        return JsonResult.success();
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
