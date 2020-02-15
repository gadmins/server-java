package com.itfenbao.gadmins.app.controller;


import com.itfenbao.gadmins.app.data.vo.MenuItem;
import com.itfenbao.gadmins.app.data.vo.MenuTree;
import com.itfenbao.gadmins.app.entity.Function;
import com.itfenbao.gadmins.app.entity.Menu;
import com.itfenbao.gadmins.app.service.IFunctionService;
import com.itfenbao.gadmins.app.service.IMenuService;
import com.itfenbao.gadmins.common.AppConfig;
import com.itfenbao.gadmins.common.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
public class MenuController {

    @Autowired
    IMenuService menuService;

    @GetMapping
    @com.itfenbao.gadmins.common.annotation.Function("sys.menu")
    public JsonResult<List<MenuItem>> test() {
        return JsonResult.success(new MenuTree(menuService.getAllMenu()).builTree());
    }
}
