package com.itfenbao.gadmins.core.web.query;

import io.swagger.annotations.ApiModelProperty;

public class PageQuery {
    @ApiModelProperty(value = "分页页码", example = "1")
    private long current = 1;
    @ApiModelProperty(value = "分页大小", example = "10")
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
