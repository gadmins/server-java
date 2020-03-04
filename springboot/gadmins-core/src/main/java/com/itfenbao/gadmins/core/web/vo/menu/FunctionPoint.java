package com.itfenbao.gadmins.core.web.vo.menu;

public class FunctionPoint {

    private Integer funcId;

    // 父id
    private Integer parentFuncId;

    private String group;
    private String code;
    private String title;
    private String desc;
    private String icon;
    private int sort;
    private boolean menu;
    private String url;
    private String parentCode;
    private String btnGroup;

    private FunctionPointConfig pointConfig;

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public Integer getParentFuncId() {
        return parentFuncId;
    }

    public void setParentFuncId(Integer parentFuncId) {
        this.parentFuncId = parentFuncId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getBtnGroup() {
        return btnGroup;
    }

    public void setBtnGroup(String btnGroup) {
        this.btnGroup = btnGroup;
    }

    public FunctionPointConfig getPointConfig() {
        return pointConfig;
    }

    public void setPointConfig(FunctionPointConfig pointConfig) {
        this.pointConfig = pointConfig;
    }

    @Override
    public String toString() {
        return "FunctionPoint{" +
                "group='" + group + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", menu=" + menu +
                ", url='" + url + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", btnGroup='" + btnGroup + '\'' +
                ", pointConfig=" + pointConfig +
                '}';
    }
}
