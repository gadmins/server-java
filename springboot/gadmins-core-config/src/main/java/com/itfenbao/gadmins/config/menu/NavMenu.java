package com.itfenbao.gadmins.config.menu;

public class NavMenu extends SysMenu {

    private String parentCode;

    public NavMenu(String title, String parentCode, String code, int sort) {
        super(title, code, sort);
        this.parentCode = parentCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
