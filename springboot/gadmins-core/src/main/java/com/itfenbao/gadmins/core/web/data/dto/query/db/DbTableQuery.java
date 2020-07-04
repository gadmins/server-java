package com.itfenbao.gadmins.core.web.data.dto.query.db;

import com.itfenbao.gadmins.core.web.query.PageQuery;

public class DbTableQuery extends PageQuery {
    String dbName;
    String tableName;
    String comment;
    String[] createdAt;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String[] createdAt) {
        this.createdAt = createdAt;
    }
}
