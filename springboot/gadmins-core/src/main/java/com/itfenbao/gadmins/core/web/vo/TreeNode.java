package com.itfenbao.gadmins.core.web.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * 通用树形节点
 *
 * @param <T>
 */
public class TreeNode<T extends TreeNode> {
    private String title;
    private String name;
    private String icon;
    private Integer id;
    private Integer parentId;
    private List<T> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @JsonIgnore
    public boolean isRoot() {
        return this.parentId == null;
    }
}