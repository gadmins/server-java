package com.itfenbao.gadmins.admin.controller;


import com.itfenbao.gadmins.admin.data.dto.param.menu.AddMenuParam;
import com.itfenbao.gadmins.admin.data.dto.param.menu.UpdateMenuParam;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Functions;
import com.itfenbao.gadmins.core.annotation.MenuFunction;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Slf4j
@RestController
@RequestMapping(AppConfig.Route.Admin.MENU)
@Api(tags = "系统菜单", hidden = AppConfig.HIDDEN_SYS_API)
@com.itfenbao.gadmins.core.annotation.Menu(value = "menu", sort = 1, parentCode = AppConfig.Menu.Nav.BASE_MGR, title = "菜单管理", desc = "系统菜单管理", url = "/system/menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @Autowired
    IFunctionService functionService;

    @MenuFunction(value = "sys.menu.list", title = "查询", desc = "查询菜单")
    @GetMapping("/tree")
    @ApiOperation("获取菜单树")
    public JsonResult<List<MenuTreeNode>> menuTree() {
        return JsonResult.success(menuService.menuTree());
    }

    @GetMapping("/tree/func")
    @ApiOperation("获取菜单树及按钮")
    public JsonResult<List<MenuTreeNode>> menuTreeAndFunc() {
        return JsonResult.success(menuService.menuTreeAndFuncs());
    }

    @GetMapping("/tree/parent")
    @ApiOperation("获取非菜单菜单树")
    public JsonResult<List<MenuTreeNode>> notMenuTree(@RequestParam(value = "filterIds", required = false) List<Integer> ids) {
        return JsonResult.success(menuService.notMenuTree(ids));
    }

    @Functions({
            @Function(value = "sys.menu.add", sort = 1, title = "添加", desc = "添加菜单", btnGroup = Function.BtnGroup.TOOLBAR),
            @Function(value = "sys.menu.copy", sort = 4, title = "复制菜单", desc = "复制菜单", btnGroup = Function.BtnGroup.TOOLBAR)
    })
    @PostMapping
    @ApiOperation("添加菜单")
    public JsonResult add(@RequestBody AddMenuParam param) {
        return this.menuService.add(param) ? JsonResult.success() : JsonResult.failMessage("编码已存在");
    }

    @Function(value = "sys.menu.edit", sort = 2, title = "编辑", desc = "编辑菜单", btnGroup = Function.BtnGroup.TOOLBAR)
    @PutMapping("/{id}")
    @ApiOperation("修改菜单")
    public JsonResult update(@PathVariable("id") Integer id, @RequestBody UpdateMenuParam param) {
        return this.menuService.updateById(id, param) ? JsonResult.success() : JsonResult.failMessage("编码已存在");
    }

    @Function(value = "sys.menu.del", sort = 3, title = "批量删除", desc = "批量删除", btnGroup = Function.BtnGroup.TOOLBAR)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除菜单")
    public JsonResult deletes(@PathVariable() List<Integer> ids) {
        menuService.removeByIds(ids);
        return JsonResult.success();
    }

    @GetMapping("/refresh")
    @ApiOperation("刷新菜单")
    public JsonResult refresh() {
        menuService.updateScanMenus();
        return JsonResult.success();
    }

    @GetMapping("/reset")
    @ApiOperation("重置菜单")
    public JsonResult reset() throws SQLException {
        menuService.resetMenus();
        return JsonResult.success();
    }

}
