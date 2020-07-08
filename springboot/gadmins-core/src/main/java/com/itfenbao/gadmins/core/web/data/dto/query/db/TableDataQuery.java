package com.itfenbao.gadmins.core.web.data.dto.query.db;

import com.itfenbao.gadmins.core.web.query.PageQuery;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/8 7:05 上午
 */
public class TableDataQuery extends PageQuery {
    String dbName;
    String tableName;
    Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
