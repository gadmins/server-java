package com.itfenbao.gadmins.core.web.data.dto.query.db;

import com.itfenbao.gadmins.core.web.query.PageQuery;

public class TableColumnQuery extends PageQuery {
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
