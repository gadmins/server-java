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


    /**
     * 路由配置
     */
    public static final class Route {
        private static final String ALL = "/**/*";

        /**
         * 管理端接口路由
         */
        public static final class Admin {
            public static final String ROOT = "/adminapi";
            public static final String ALL = ROOT + Route.ALL;
            public static final String ACCOUNT = ROOT + "/account";
            public static final String FUNCTION = ROOT + "/function";
            public static final String MENU = ROOT + "/menu";
            public static final String ROLE = ROOT + "/role";
            public static final String DICT = ROOT + "/dict";
            public static final String SETTINGS = ROOT + "/settings";
            public static final String DB = ROOT + "/db";
            public static final String DATAWAY = ROOT + "/dataway";
            public static final String TOOL = ROOT + "/tool";
        }

        /**
         * App端接口路由
         */
        public static final class App {
            public static final String ROOT = "/appapi";
            public static final String ALL = ROOT + Route.ALL;
        }
    }

    /**
     * 菜单配置
     */
    public static final class Menu {
        public static final class Type {
            public static final String SYS_MENU = "SYS_MENU";
            public static final String NAV_MENU = "NAV_MENU";
            public static final String MENU = "MENU";
        }

        /**
         * 系统菜单
         */
        public static final class Sys {
            // 默认菜单 -- start --
            public static final String HOME = "home";
            public static final String SYSTEM = "system";
            // 默认菜单 -- end --
        }

        /**
         * 导航菜单
         */
        public static final class Nav {
            // 默认菜单 -- start --
            public static final String BASE_MGR = "basemgr";
            public static final String SETTINGS = "settings";
            public static final String DEVOPS = "devops";
            // 默认菜单 -- end --
        }
    }

    public static final boolean HIDDEN_SYS_API = false;

    // 统一接口组 描述
    public static final Group[] GROUPS = new Group[]{
            new Group("adminapi", "后台接口", Route.Admin.ROOT),
            new Group("appapi", "APP接口", Route.App.ROOT)
    };

    private static class Group {
        String type;
        String label;
        String urlPrefix;

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
