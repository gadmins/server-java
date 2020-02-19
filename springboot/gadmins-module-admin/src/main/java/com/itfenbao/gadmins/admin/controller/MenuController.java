package com.itfenbao.gadmins.admin.controller;


import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.vo.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(AppConfig.AdminRoute.ADMIN_MENU)
@Api(tags = "系统菜单")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @GetMapping("/tree")
    @ApiOperation("获取菜单树")
    public JsonResult<List<Tree.TreeNode>> menuTree() {
        return JsonResult.success(menuService.menuTree());
    }

}
