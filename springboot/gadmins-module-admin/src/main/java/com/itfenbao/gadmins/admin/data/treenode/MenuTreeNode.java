package com.itfenbao.gadmins.admin.data.treenode;

import com.itfenbao.gadmins.core.web.vo.TreeNode;


public class MenuTreeNode extends TreeNode<MenuTreeNode> {
    private String key;
    private Integer sortNumber;
    private Integer funcId;
    private String type;
    private Boolean elink;
    private String url;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getElink() {
        return elink;
    }

    public void setElink(Boolean elink) {
        this.elink = elink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
