package com.itfenbao.gadmins.admin.data.vo;

import com.itfenbao.gadmins.admin.data.treenode.SysMenuTreeNode;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author itfenbao
 */
@Data
public class CoreMenuData {
    private List<SysMenuTreeNode> menus;
    private List<AuthFunciontVO> authFuncs;
    private Map<String, String> defTex;

    public CoreMenuData(List<SysMenuTreeNode> menus, Map<String, String> defTex) {
        this.menus = menus;
        this.defTex = defTex;
    }

    public CoreMenuData(List<SysMenuTreeNode> menus, List<AuthFunciontVO> authFuncs, Map<String, String> defTex) {
        this.menus = menus;
        this.authFuncs = authFuncs;
        this.defTex = defTex;
    }
}
