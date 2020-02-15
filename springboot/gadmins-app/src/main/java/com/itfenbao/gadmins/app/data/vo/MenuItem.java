package com.itfenbao.gadmins.app.data.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class MenuItem {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer parentId;

    private String code;
    private String title;
    private String icon;
    private String path;
    private List<MenuItem> authBtns;
    private List<MenuItem> menus;

    @JsonIgnore
    public boolean isRoot() {
        return this.parentId == null;
    }
}
