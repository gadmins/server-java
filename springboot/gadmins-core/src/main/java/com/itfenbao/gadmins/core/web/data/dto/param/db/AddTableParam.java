package com.itfenbao.gadmins.core.web.data.dto.param.db;

public class AddTableParam extends UpdateTableParam {
    String name;
    boolean hasDelete;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(boolean hasDelete) {
        this.hasDelete = hasDelete;
    }
}
