package com.itfenbao.gadmins.app.data.vo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
    private List<MenuItem> menuList = new ArrayList<MenuItem>();

    public MenuTree(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    //建立树形结构
    public List<MenuItem> builTree() {
        List<MenuItem> treeMenus = new ArrayList<MenuItem>();
        for (MenuItem menuNode : getRootNode()) {
            menuNode = buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private MenuItem buildChilTree(MenuItem pNode) {
        List<MenuItem> chilMenus = new ArrayList<MenuItem>();
        for (MenuItem menuNode : menuList) {
            if (menuNode.getParentId() != null && menuNode.getParentId().equals(pNode.getId())) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        if (!CollectionUtils.isEmpty(chilMenus)) {
            pNode.setMenus(chilMenus);
        }
        return pNode;
    }

    //获取根节点
    private List<MenuItem> getRootNode() {
        List<MenuItem> rootMenuLists = new ArrayList<MenuItem>();
        for (MenuItem menuNode : menuList) {
            if (menuNode.isRoot()) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
}
