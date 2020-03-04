package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.admin.data.dto.param.menu.AddMenuParam;
import com.itfenbao.gadmins.admin.data.dto.param.menu.UpdateMenuParam;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.entity.Menu;
import com.itfenbao.gadmins.admin.service.IFunctionConfigService;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.AppListener;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Functions;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPoint;
import com.itfenbao.gadmins.core.web.vo.menu.MenuConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
@com.itfenbao.gadmins.core.annotation.Menu(value = "menu", parentCode = AppConfig.SysNavMenu.BASE_MGR, title = "菜单管理", desc = "系统菜单管理", url = "/system/menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @Autowired
    IFunctionService functionService;

    @Autowired
    IFunctionConfigService functionConfigService;

    @Autowired
    AppListener appListener;

    @Function(value = "sys.menu.list", sort = 0, title = "查询", menu = true)
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
            @Function(value = "sys.menu.add", sort = 1, title = "添加菜单", btnGroup = Function.BtnGroup.TOOLBAR),
            @Function(value = "sys.menu.copy", sort = 4, title = "复制菜单", btnGroup = Function.BtnGroup.TOOLBAR)
    })
    @PostMapping
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

    @Function(value = "sys.menu.edit", sort = 2, title = "编辑", btnGroup = Function.BtnGroup.TOOLBAR)
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

    @Function(value = "sys.menu.del", sort = 3, title = "批量删除", btnGroup = Function.BtnGroup.TOOLBAR)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除菜单")
    public JsonResult deletes(@PathVariable() List<Integer> ids) {
        menuService.removeByIds(ids);
        return JsonResult.success();
    }

    @GetMapping("/refresh")
    @ApiOperation("刷新菜单")
    public JsonResult refresh() {
        List<MenuConfig> menuConfigs = appListener.getMenuConfigs();
        menuConfigs.forEach(mc -> {
            AtomicReference<Integer> funcId = new AtomicReference<>();
            // 先处理 parentCode 为空
            mc.getFunctionPoints().stream().filter(f -> StringUtils.isEmpty(f.getParentCode())).forEach(fp -> {
                saveFunctionPointAndConfig(funcId, fp);
            });
            mc.getFunctionPoints().stream().filter(f -> !StringUtils.isEmpty(f.getParentCode())).forEach(fp -> {
                saveFunctionPointAndConfig(funcId, fp);
            });
            if (funcId.get() != null) {
                mc.setFuncId(funcId.get());
            }
            menuService.saveOrUpdate(mc);
        });
        return JsonResult.success();
    }

    private void saveFunctionPointAndConfig(AtomicReference<Integer> funcId, FunctionPoint fp) {
        if (funcId.get() != null) {
            fp.setParentFuncId(funcId.get());
        }
        if (functionService.saveOrUpdate(fp)) {
            if (fp.isMenu()) {
                funcId.set(fp.getFuncId());
            }
            fp.getPointConfig().setFuncId(fp.getFuncId());
            functionConfigService.saveOrUpdate(fp.getPointConfig());
        }
    }

}
