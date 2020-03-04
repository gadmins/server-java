package com.itfenbao.gadmins.core.web.vo.menu;

import java.util.List;

public class MenuConfig {

    private String code;
    private String parentCode;
    private String title;
    private String desc;
    private String url;
    private String icon;
    private int sort;
    private Integer funcId;

    private List<FunctionPoint> functionPoints;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public List<FunctionPoint> getFunctionPoints() {
        return functionPoints;
    }

    public void setFunctionPoints(List<FunctionPoint> functionPoints) {
        this.functionPoints = functionPoints;
    }

    @Override
    public String toString() {
        return "MenuConfig{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", functionPoints=" + functionPoints +
                '}';
    }
}
