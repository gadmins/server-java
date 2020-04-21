package com.itfenbao.gadmins.config;

/**
 * 应用的常量配置
 * 1、管理端：后台管理端
 * 2、APP端：业务用户端
 */
public final class AppConfig {

    public enum TokenType {
        ADMIN,
        APP
    }

    /**
     * 访问标示
     */
    public static final class Access {
        public static final String ADMIN_TOKEN = "Admin-Token";
        public static final String APP_TOKEN = "Access-Token";
    }

    private static final String ALL = "/**/*";

    /**
     * 管理端接口路由
     */
    public static final class AdminRoute {
        public static final String ADMIN = "/adminapi";
        public static final String ADMIN_ALL = "/adminapi" + ALL;
        public static final String ADMIN_ACCOUNT = ADMIN + "/account";
        public static final String ADMIN_FUNCTION = ADMIN + "/function";
        public static final String ADMIN_MENU = ADMIN + "/menu";
        public static final String ADMIN_ROLE = ADMIN + "/role";
        public static final String ADMIN_DICT = ADMIN + "/dict";
        public static final String ADMIN_SETTINGS = ADMIN + "/settings";
        public static final String ADMIN_DB = ADMIN + "/db";
        public static final String ADMIN_DATAWAY = ADMIN + "/dataway";
        public static final String ADMIN_TOOL = ADMIN + "/tool";
    }

    /**
     * App端接口路由
     */
    public static final class AppRoute {
        public static final String APP = "/appapi";
        public static final String APP_ALL = "/appapi" + ALL;
    }

    public static final class MenuType {
        //        public static final String SYS_MENU = "SYS_MENU";
//        public static final String NAV_MENU = "NAV_MENU";
        public static final String MENU = "MENU";
    }

    /**
     * 系统内置导航菜单，便于菜单挂载
     */
    public static final class SysNavMenu {
        public static final String BASE_MGR = "basemgr";
        public static final String SETTINGS = "settings";
        public static final String DEVOPS = "devops";
    }

    public static final boolean HIDDEN_SYS_API = false;

    // 统一接口组 描述
    public static final Group[] GROUPS = new Group[]{
            new Group("adminapi", "后台接口", AdminRoute.ADMIN)
    };

    private static class Group {
        String type;
        String label;
        String urlPrefix;

        public Group() {
        }

        public Group(String type, String label, String urlPrefix) {
            this.type = type;
            this.label = label;
            this.urlPrefix = urlPrefix;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getUrlPrefix() {
            return urlPrefix;
        }

        public void setUrlPrefix(String urlPrefix) {
            this.urlPrefix = urlPrefix;
        }
    }

}
