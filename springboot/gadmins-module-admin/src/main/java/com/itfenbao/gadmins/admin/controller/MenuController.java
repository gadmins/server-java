package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.admin.data.dto.param.menu.AddMenuParam;
import com.itfenbao.gadmins.admin.data.dto.param.menu.UpdateMenuParam;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.entity.Menu;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(AppConfig.AdminRoute.ADMIN_MENU)
@Api(tags = "系统菜单")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @Autowired
    IFunctionService functionService;

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

    @PostMapping
    @Function(value = "sys.menu.add", title = "添加菜单", btnGroup = Function.BtnGroup.TOOLBAR)
    @ApiOperation("添加菜单")
    public JsonResult add(@RequestBody AddMenuParam param) {
        int count = menuService.count(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, param.getMcode()));
        if (count > 0) {
            return JsonResult.failMessage("编码已存在");
        }
        Menu menu = new Menu();
        menu.setPId(param.getParentId());
        menu.setType(param.getType());
        menu.setTxt(param.getTxt());
        menu.setMCode(param.getMcode());
        menu.setIcon(param.getIcon());
        menu.setSortNumber(param.getSortNumber());
        if (param.getFuncId() != null) {
            menu.setFuncId(param.getFuncId());
            com.itfenbao.gadmins.admin.entity.Function function = new com.itfenbao.gadmins.admin.entity.Function();
            function.setId(param.getFuncId());
            function.setElink(param.getElink());
            function.setFrontUrl(param.getUrl());
            functionService.updateById(function);
        }
        menuService.save(menu);

        return JsonResult.success();
    }

    @PutMapping("/{id}")
    @ApiOperation("修改菜单")
    public JsonResult update(@PathVariable("id") Integer id, @RequestBody UpdateMenuParam param) {
        int count = menuService.count(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, param.getMcode()).ne(Menu::getId, id));
        if (count > 0) {
            return JsonResult.failMessage("编码已存在");
        }
        Menu menu = new Menu();
        menu.setId(id);
        menu.setPId(param.getParentId());
        menu.setTxt(param.getTxt());
        menu.setMCode(param.getMcode());
        menu.setIcon(param.getIcon());
        menu.setSortNumber(param.getSortNumber());
        if (param.getFuncId() != null) {
            menu.setFuncId(param.getFuncId());
            com.itfenbao.gadmins.admin.entity.Function function = new com.itfenbao.gadmins.admin.entity.Function();
            function.setId(param.getFuncId());
            function.setElink(param.getElink());
            function.setFrontUrl(param.getUrl());
            functionService.updateById(function);
        }
        menuService.updateById(menu);
        return JsonResult.success();
    }

    @DeleteMapping("/{ids}")
    @ApiOperation("删除菜单")
    public JsonResult deletes(@PathVariable() List<Integer> ids) {
        menuService.removeByIds(ids);
        return JsonResult.success();
    }
}
