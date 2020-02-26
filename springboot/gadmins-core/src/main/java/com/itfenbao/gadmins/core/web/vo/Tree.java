package com.itfenbao.gadmins.core.web.vo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 通用树形
 *
 * @param <T>
 * @author itfebao
 */
public class Tree<T extends TreeNode> {
    private List<T> treeNodes = new ArrayList<T>();

    private Tree(List<T> treeNodes) {
        this.treeNodes = treeNodes;
    }

    //建立树形结构
    public List<T> builTree() {
        List<T> treeMenus = new ArrayList<T>();
        for (T menuNode : getRootNode()) {
            menuNode = buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //建立树形结构
    public List<T> builTree(Consumer<? super T> action) {
        List<T> treeMenus = new ArrayList<T>();
        for (T menuNode : getRootNode()) {
            menuNode = buildChilTree(menuNode, action);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private T buildChilTree(T pNode) {
        List<T> chilMenus = new ArrayList<T>();
        for (T menuNode : treeNodes) {
            if (menuNode.getParentId() != null && menuNode.getParentId().equals(pNode.getId())) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        if (!CollectionUtils.isEmpty(chilMenus)) {
            pNode.setChildren(chilMenus);
        }
        return pNode;
    }

    //递归，建立子树形结构
    private T buildChilTree(T pNode, Consumer<? super T> action) {
        List<T> chilMenus = new ArrayList<T>();
        for (T menuNode : treeNodes) {
            if (menuNode.getParentId() != null && menuNode.getParentId().equals(pNode.getId())) {
                chilMenus.add(buildChilTree(menuNode, action));
            }
        }
        if (!CollectionUtils.isEmpty(chilMenus)) {
            pNode.setChildren(chilMenus);
        }
        action.accept(pNode);
        return pNode;
    }

    //获取根节点
    private List<T> getRootNode() {
        List<T> rootMenuLists = new ArrayList<T>();
        for (T menuNode : treeNodes) {
            if (menuNode.isRoot()) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }

    /**
     * 构造树形数据
     *
     * @param treeNodes
     * @param <T>
     * @return
     */
    public static <T extends TreeNode> List<T> build(List<T> treeNodes) {
        return new Tree<T>(treeNodes).builTree();
    }

    public static <T extends TreeNode> List<T> build(List<T> treeNodes, Consumer<? super T> action) {
        return new Tree<T>(treeNodes).builTree(action);
    }

}
