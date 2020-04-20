package com.itfenbao.gadmins.core.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.core.web.query.PageQuery;

public class PageUtils {
    public static <T> IPage<T> page(PageQuery query) {
        return new Page<T>(query.getCurrent(), query.getPageSize());
    }
}
