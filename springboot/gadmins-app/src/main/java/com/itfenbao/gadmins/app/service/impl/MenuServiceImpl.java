package com.itfenbao.gadmins.app.service.impl;

import cn.hutool.core.map.CamelCaseLinkedMap;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.app.data.vo.CoreMenuData;
import com.itfenbao.gadmins.app.data.vo.MenuItem;
import com.itfenbao.gadmins.app.data.vo.MenuTree;
import com.itfenbao.gadmins.app.data.vo.MenuVO;
import com.itfenbao.gadmins.app.entity.Function;
import com.itfenbao.gadmins.app.entity.Menu;
import com.itfenbao.gadmins.app.mapper.MenuMapper;
import com.itfenbao.gadmins.app.service.IFunctionService;
import com.itfenbao.gadmins.app.service.IMenuService;
import com.itfenbao.gadmins.core.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    IFunctionService functionService;

    @Override
    public CoreMenuData getCoreMenuData() {
        List<MenuVO> allMenu = this.baseMapper.getAllMenu();
        Map<String, String> defMenuTxtMap = new CamelCaseLinkedMap();
        List<MenuItem> menuItems = allMenu.stream().sorted(Comparator.comparing(Menu::getSortNumber)).map(menu -> {
            defMenuTxtMap.put(menu.getMCode(), menu.getTxt());
            MenuItem menuItem = new MenuItem();
            menuItem.setId(menu.getId());
            menuItem.setFuncId(menu.getFunId());
            menuItem.setParentId(menu.getPId());
            menuItem.setName(menu.getMCode());
            menuItem.setIcon(menu.getIcon());
            menuItem.setPath(menu.getFrontUrl());
            if (AppConfig.MenuType.MENU.equals(menu.getType()) && menu.getFunId() != null) {
                List<Function> functions = functionService.lambdaQuery().eq(Function::getPId, menu.getFunId()).list();
                List<MenuItem> funcs = functions.stream().map(func -> {
                    MenuItem authBtn = new MenuItem();
                    authBtn.setFuncId(func.getId());
                    authBtn.setName(func.getTitle());
                    authBtn.setPath(func.getFrontUrl());
                    return authBtn;
                }).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(funcs)) {
                    menuItem.setFuncs(funcs);
                }
            }
            return menuItem;
        }).collect(Collectors.toList());
        return new CoreMenuData(new MenuTree(menuItems).builTree(), defMenuTxtMap);
    }
}
