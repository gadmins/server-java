package com.itfenbao.gadmins.admin.service.impl;

import cn.hutool.core.map.CamelCaseLinkedMap;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.data.treenode.SysMenuTreeNode;
import com.itfenbao.gadmins.admin.data.vo.CoreMenuData;
import com.itfenbao.gadmins.admin.data.vo.MenuVO;
import com.itfenbao.gadmins.admin.entity.Menu;
import com.itfenbao.gadmins.admin.mapper.MenuMapper;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.core.web.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//            if (AppConfig.MenuType.MENU.equals(menu.getType()) && menu.getFunId() != null) {
//                List<Function> functions = functionService.lambdaQuery().eq(Function::getPId, menu.getFunId()).list();
//                List<MenuItem> funcs = functions.stream().map(func -> {
//                    MenuItem authBtn = new MenuItem();
//                    authBtn.setFuncId(func.getId());
//                    authBtn.setName(func.getTitle());
//                    authBtn.setPath(func.getFrontUrl());
//                    return authBtn;
//                }).collect(Collectors.toList());
//                if (!CollectionUtils.isEmpty(funcs)) {
//                    menuItem.setFuncs(funcs);
//                }
//            }
            return sysMenuTreeNode;
        }).collect(Collectors.toList());
        List<SysMenuTreeNode> menuTree = Tree.build(sysMenuTreeNodes);
        menuTree.stream().forEach(item -> {
            item.setPath(getPath(item));
        });
        return new CoreMenuData(menuTree, defMenuTxtMap);
    }

    private String getPath(SysMenuTreeNode item) {
        if (item.getChildren() == null || item.getChildren().size() == 0) {
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
    public List<MenuTreeNode> notMenuTree(List<Integer> ids) {
        return Tree.build(this.baseMapper.getAllParentMenuTree());
    }
}
