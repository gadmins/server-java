package com.itfenbao.gadmins.core.web.data.dto.param.db;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/6 9:22 上午
 */
public class AddColumnParam extends CommonColumnParam {
    String dbName;
    String tableName;
    String columnName;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
