package com.itfenbao.gadmins.core.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/6 8:47 上午
 */
public class AlterTableUtils {
    public static String commentSql(String comment) {
        return String.format("comment '%s'", comment);
    }

    public static String dropColumnSql(String columnName) {
        return String.format("DROP COLUMN %s", columnName);
    }

    public static String addColumnSql(String name, String type, boolean isNull, String defaultVal, String comment) {
        return String.format("ADD %s %s %s %s %s",
                name,
                type,
                isNull ? "" : "NOT NULL",
                StringUtils.isBlank(defaultVal) ? "" : String.format("DEFAULT '%s'", defaultVal),
                StringUtils.isBlank(comment) ? "" : String.format("COMMENT '%s'", comment)
        );
    }

    public static String updateColumnSql(String name, String newName, String type, boolean isNull, String defaultVal, String comment) {
        if (name.equalsIgnoreCase(newName)) {
            return String.format("MODIFY COLUMN %s %s %s %s %s",
                    name,
                    type,
                    isNull ? "" : "NOT NULL",
                    StringUtils.isBlank(defaultVal) ? "" : String.format("DEFAULT '%s'", defaultVal),
                    StringUtils.isBlank(comment) ? "" : String.format("COMMENT '%s'", comment)
            );
        }
        return String.format("CHANGE %s %s %s %s %s %s",
                name,
                newName,
                type,
                isNull ? "" : "NOT NULL",
                StringUtils.isBlank(defaultVal) ? "" : String.format("DEFAULT '%s'", defaultVal),
                StringUtils.isBlank(comment) ? "" : String.format("COMMENT '%s'", comment)
        );
    }
}
