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
    private Integer funcId;
    private String name;
    private String icon;
    private String path;
    private List<MenuItem> funcs;
    private List<MenuItem> children;

    @JsonIgnore
    public boolean isRoot() {
        return this.parentId == null;
    }
}
