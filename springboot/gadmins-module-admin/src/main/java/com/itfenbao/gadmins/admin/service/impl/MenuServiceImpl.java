package com.itfenbao.gadmins.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.CamelCaseLinkedMap;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.data.treenode.SysMenuTreeNode;
import com.itfenbao.gadmins.admin.data.vo.CoreMenuData;
import com.itfenbao.gadmins.admin.data.vo.MenuVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.admin.entity.Menu;
import com.itfenbao.gadmins.admin.entity.RlMenuRole;
import com.itfenbao.gadmins.admin.mapper.MenuMapper;
import com.itfenbao.gadmins.admin.service.*;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.web.vo.Tree;
import com.itfenbao.gadmins.core.web.vo.menu.MenuConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    IFunctionService functionService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IRlAccountRoleService accountRoleService;

    @Autowired
    IRlMenuRoleService menuRoleService;

    @Override
    public boolean saveOrUpdate(MenuConfig menuConfig) {
        Menu one = this.getOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, menuConfig.getCode()));
        if (one == null) {
            Menu menu = createMenu(menuConfig);
            if (this.save(menu)) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    private Menu createMenu(MenuConfig menuConfig) {
        Menu menu = new Menu();
        menu.setSortNumber(menuConfig.getSort());
        menu.setFuncId(menuConfig.getFuncId());
        menu.setMCode(menuConfig.getCode());
        menu.setTxt(menuConfig.getTitle());
        menu.setIcon(menuConfig.getIcon());
        if (!StringUtils.isEmpty(menuConfig.getParentCode())) {
            Menu getMenu = this.getOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, menuConfig.getParentCode()));
            if (getMenu != null) {
                menu.setPId(getMenu.getId());
            }
        }
        menuConfig.getParentCode();
        return menu;
    }

    @Override
    public CoreMenuData getCoreMenuData(Integer accountId) {
        boolean isSuperAdmin = accountService.isSuperAdmin(accountId);
        List<MenuVO> allMenu = this.baseMapper.getAllMenu();
        if (!isSuperAdmin) {
            List<Integer> roleIds = accountRoleService.getRoleIdsByAccountId(accountId);
            List<Integer> funcPids = this.functionService.getPIdsByRoles(roleIds);
            // 查询角色菜单ids
            LambdaQueryWrapper<RlMenuRole> wrapper = Wrappers.<RlMenuRole>lambdaQuery();
            if (!CollectionUtils.isEmpty(roleIds)) {
                wrapper.in(RlMenuRole::getRoleId, roleIds);
            }
            wrapper.groupBy(RlMenuRole::getMenuId);
            List<Integer> roleMenuIds = this.menuRoleService.list(wrapper)
                    .stream().map(it -> it.getMenuId()).collect(Collectors.toList());
            // 查询功能菜单ids
            LambdaQueryWrapper<Menu> menuWrapper = Wrappers.<Menu>lambdaQuery();
            if (!CollectionUtils.isEmpty(funcPids)) {
                menuWrapper.in(Menu::getFuncId, funcPids);
            }
            List<Integer> funcMenuIds = this.list(menuWrapper)
                    .stream().map((it -> it.getId())).collect(Collectors.toList());

            // TODO: 合成用户菜单，待优化
            // 1. 去重
            List<Integer> menuIds = CollUtil.addAllIfNotContains(roleMenuIds, funcMenuIds);
            // 2. 在所有菜单找到自己及父级菜单
            List<Integer> pids = CollUtil.newArrayList(menuIds);
            do {
                List<Integer> finalPids = pids;
                pids = allMenu.stream().filter(m -> finalPids.indexOf(m.getId()) > -1 && m.getPId() != null)
                        .map(m -> m.getPId()).collect(Collectors.toList());
                if (pids.size() > 0) {
                    CollUtil.addAllIfNotContains(menuIds, pids);
                }
            } while (pids.size() > 0);
            allMenu = allMenu.stream().filter(m -> menuIds.indexOf(m.getId()) > -1).collect(Collectors.toList());
        }
        Map<String, String> defMenuTxtMap = new CamelCaseLinkedMap();
        List<SysMenuTreeNode> sysMenuTreeNodes = allMenu.stream().sorted(Comparator.comparing(Menu::getSortNumber)).map(menu -> {
            defMenuTxtMap.put(menu.getMCode(), menu.getTxt());
            SysMenuTreeNode sysMenuTreeNode = new SysMenuTreeNode();
            sysMenuTreeNode.setId(menu.getId());
            sysMenuTreeNode.setFuncId(menu.getFuncId());
            sysMenuTreeNode.setParentId(menu.getPId());
            sysMenuTreeNode.setKey(menu.getMCode());
            sysMenuTreeNode.setName(menu.getMCode());
            sysMenuTreeNode.setIcon(menu.getIcon());
            sysMenuTreeNode.setPath(menu.getFrontUrl());
            if (menu.getElink()) {
                sysMenuTreeNode.setTarget("_blank");
            }
            return sysMenuTreeNode;
        }).collect(Collectors.toList());
        List<SysMenuTreeNode> menuTree = Tree.build(sysMenuTreeNodes);
        menuTree.stream().forEach(item -> {
            item.setPath(getPath(item));
        });
        return new CoreMenuData(menuTree, defMenuTxtMap);
    }

    /**
     * 设置父级菜单Path
     *
     * @param item
     * @return
     */
    private String getPath(SysMenuTreeNode item) {
        if (CollectionUtils.isEmpty(item.getChildren())) {
            return item.getPath();
        } else {
            return getPath(item.getChildren().get(0));
        }
    }


    @Override
    public List<MenuTreeNode> menuTree() {
        return Tree.build(this.baseMapper.getAllMenuTree());
    }

    @Override
    public List<MenuTreeNode> menuTreeAndFuncs() {
        return Tree.build(this.baseMapper.getAllMenuTree(), menu -> {
            if (AppConfig.MenuType.MENU.equals(menu.getType())) {
                if (AppConfig.MenuType.MENU.equals(menu.getType()) && menu.getFuncId() != null) {
                    List<Function> functions = functionService.lambdaQuery().eq(Function::getPId, menu.getFuncId()).list();
                    Function queryFunc = functionService.getById(menu.getFuncId());
                    if (StringUtils.isEmpty(queryFunc.getTitle())) {
                        queryFunc.setTitle("查询");
                    }
                    functions.add(0, queryFunc);
                    List<MenuTreeNode> funcs = functions.stream().map(func -> {
                        MenuTreeNode authBtn = new MenuTreeNode();
                        authBtn.setType("FUNC");
                        authBtn.setId(func.getId());
                        authBtn.setKey(func.getFuncCode());
                        authBtn.setTitle(func.getTitle());
                        return authBtn;
                    }).collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(funcs)) {
                        menu.setChildren(funcs);
                    }
                }
            }
        });
    }

    @Override
    public List<MenuTreeNode> notMenuTree(List<Integer> ids) {
        return Tree.build(this.baseMapper.getAllParentMenuTree(ids));
    }
}
