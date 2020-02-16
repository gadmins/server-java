package com.itfenbao.gadmins.core.web.query;

public class PageQuery {
    private long current = 1;
    private long pageSize = 10;

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
