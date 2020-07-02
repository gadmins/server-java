package com.itfenbao.gadmins.config.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuConfig {
    private List<SysMenu> sysMenus;
    private List<NavMenu> navMenus;

    public MenuConfig() {
        this.sysMenus = new ArrayList<>();
        this.navMenus = new ArrayList<>();
    }

    public void addSysMenu(SysMenu menu) {
        this.sysMenus.add(menu);
    }

    public void addNavMenu(NavMenu menu) {
        this.navMenus.add(menu);
    }

    public List<SysMenu> getSysMenus() {
        sysMenus.sort(Comparator.comparing(SysMenu::getSort).thenComparing(e -> e.getSort()));
        return sysMenus;
    }

    public List<NavMenu> getNavMenus() {
        navMenus.sort(Comparator.comparing(NavMenu::getSort).thenComparing(e -> e.getSort()));
        return navMenus;
    }
}
