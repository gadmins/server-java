package com.itfenbao.gadmins.core.web.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    public static class TreeNode {
        private String title;
        private String key;
        private String type;
        private Integer sortNumber;
        private Integer funId;
        private Integer id;
        @JsonIgnore
        private Integer parentId;
        private List<TreeNode> children;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getSortNumber() {
            return sortNumber;
        }

        public void setSortNumber(Integer sortNumber) {
            this.sortNumber = sortNumber;
        }

        public Integer getFunId() {
            return funId;
        }

        public void setFunId(Integer funId) {
            this.funId = funId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode> children) {
            this.children = children;
        }

        @JsonIgnore
        public boolean isRoot() {
            return this.parentId == null;
        }
    }

    private List<TreeNode> treeNodes = new ArrayList<TreeNode>();

    public Tree(List<TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
    }

    //建立树形结构
    public List<TreeNode> builTree() {
        List<TreeNode> treeMenus = new ArrayList<TreeNode>();
        for (TreeNode menuNode : getRootNode()) {
            menuNode = buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private TreeNode buildChilTree(TreeNode pNode) {
        List<TreeNode> chilMenus = new ArrayList<TreeNode>();
        for (TreeNode menuNode : treeNodes) {
            if (menuNode.getParentId() != null && menuNode.getParentId().equals(pNode.getId())) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        if (!CollectionUtils.isEmpty(chilMenus)) {
            pNode.setChildren(chilMenus);
        }
        return pNode;
    }

    //获取根节点
    private List<TreeNode> getRootNode() {
        List<TreeNode> rootMenuLists = new ArrayList<TreeNode>();
        for (TreeNode menuNode : treeNodes) {
            if (menuNode.isRoot()) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
}
