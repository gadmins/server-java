package com.itfenbao.gadmins.core.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public class PageList<T> {
    private List<T> data;
    private long current = 1;
    private long pageSize = 10;
    private long total = 0;

    private PageList() {
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

    public final static <E> PageList<E> get(IPage<E> page) {
        PageList<E> pageList = new PageList<E>();
        pageList.setCurrent(page.getCurrent());
        pageList.setPageSize(page.getSize());
        pageList.setTotal(page.getTotal());
        pageList.setData(page.getRecords());
        return pageList;
    }
}
