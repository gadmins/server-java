package com.itfenbao.gadmins.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.app.data.vo.MenuItem;
import com.itfenbao.gadmins.app.data.vo.MenuVO;
import com.itfenbao.gadmins.app.entity.Function;
import com.itfenbao.gadmins.app.entity.Menu;
import com.itfenbao.gadmins.app.mapper.MenuMapper;
import com.itfenbao.gadmins.app.service.IFunctionService;
import com.itfenbao.gadmins.app.service.IMenuService;
import com.itfenbao.gadmins.common.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
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
    public List<MenuItem> getAllMenu() {
        List<MenuVO> allMenu = this.baseMapper.getAllMenu();
        return allMenu.stream().sorted(Comparator.comparing(Menu::getSortNumber)).map(menu -> {
            MenuItem menuItem = new MenuItem();
            menuItem.setId(menu.getId());
            menuItem.setParentId(menu.getPId());
            menuItem.setCode(menu.getMCode());
            menuItem.setTitle(menu.getTxt());
            menuItem.setIcon(menu.getIcon());
            menuItem.setPath(menu.getFrontUrl());
            if (AppConfig.MenuType.MENU.equals(menu.getType()) && menu.getFunId() != null) {
                List<Function> functions = functionService.lambdaQuery().eq(Function::getPId, menu.getFunId()).list();
                List<MenuItem> authBtns = functions.stream().map(func -> {
                    MenuItem authBtn = new MenuItem();
                    authBtn.setCode(func.getFunCode());
                    authBtn.setTitle(func.getTitle());
                    authBtn.setPath(func.getFrontUrl());
                    return authBtn;
                }).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(authBtns)) {
                    menuItem.setAuthBtns(authBtns);
                }
            }
            return menuItem;
        }).collect(Collectors.toList());
    }
}
