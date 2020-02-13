package com.itfenbao.gadmins.common;

public final class AdminConfig {
    public static final class AdminRoute {
        private static final String ADMIN = "/admin";
        public static final String ADMIN_FUNCTION = ADMIN + "/function";
        public static final String ADMIN_FUNCTION_CONFIG = ADMIN + "/functionconfig";
        public static final String ADMIN_LOG = ADMIN + "/log";
        public static final String ADMIN_MENU = ADMIN + "/menu";
        public static final String ADMIN_RL_FUNCTION_ROLE = ADMIN + "/rlfunctionrole";
        public static final String ADMIN_RL_MENU_ROLE = ADMIN + "/rlmenurole";
        public static final String ADMIN_RL_USER_ROLE = ADMIN + "/rluserrole";
        public static final String ADMIN_RL_ROLE = ADMIN + "/rlrole";
        public static final String ADMIN_ROLE = ADMIN + "/role";
        public static final String ADMIN_USER = ADMIN + "/user";
    }

    public static final class AppRoute {
        private static final String ADMIN = "/app";
    }
}
