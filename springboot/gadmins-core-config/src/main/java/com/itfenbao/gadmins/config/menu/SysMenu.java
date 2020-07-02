package com.itfenbao.gadmins.config.menu;

public class SysMenu {
    private String title;
    private String code;
    private int sort;

    public SysMenu(String title, String code, int sort) {
        this.title = title;
        this.code = code;
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
