package com.itfenbao.gadmins.app.data.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CoreMenuData {
    private List<MenuItem> menus;
    private Map<String,String> defTex;

    public CoreMenuData(List<MenuItem> menus, Map<String, String> defTex) {
        this.menus = menus;
        this.defTex = defTex;
    }
}
