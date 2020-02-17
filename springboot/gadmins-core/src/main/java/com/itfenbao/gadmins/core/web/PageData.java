package com.itfenbao.gadmins.core.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PageData<T> {
    @ApiModelProperty("分页数据")
    private List<T> data;
    @ApiModelProperty(value = "分页页码", example = "1")
    private long current = 1;
    @ApiModelProperty(value = "分页大小", example = "10")
    private long pageSize = 10;
    @ApiModelProperty("总数")
    private long total = 0;

    private PageData() {
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public final static <E> PageData<E> get(IPage<E> page) {
        PageData<E> pageList = new PageData<E>();
        pageList.setCurrent(page.getCurrent());
        pageList.setPageSize(page.getSize());
        pageList.setTotal(page.getTotal());
        pageList.setData(page.getRecords());
        return pageList;
    }
}
