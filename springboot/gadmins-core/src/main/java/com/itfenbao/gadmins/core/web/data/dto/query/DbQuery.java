package com.itfenbao.gadmins.core.web.data.dto.query;

import com.itfenbao.gadmins.core.web.query.PageQuery;

public class DbQuery extends PageQuery {
    String dbName;
    String name;
    String comment;
    String[] createdAt;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
