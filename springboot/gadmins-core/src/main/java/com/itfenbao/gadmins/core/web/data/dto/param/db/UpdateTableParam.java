package com.itfenbao.gadmins.core.web.data.dto.param.db;

public class UpdateTableParam {
    String newName;
    String comment;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
