package com.itfenbao.gadmins.admin.data.treenode;

import com.itfenbao.gadmins.core.web.vo.TreeNode;

public class SysMenuTreeNode extends TreeNode<SysMenuTreeNode> {
    private Integer funcId;
    private String path;
    private String key;
    private String name;

    private String target;

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
