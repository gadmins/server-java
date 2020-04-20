package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.admin.data.dto.query.DbQuery;
import com.itfenbao.gadmins.admin.mapper.DbMapper;
import com.itfenbao.gadmins.admin.service.IDbService;
import com.itfenbao.gadmins.core.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class DbService implements IDbService {

    @Autowired
    DbMapper mapper;

    @Override
    public IPage<Map> listTableByPage(DbQuery query) {
        IPage<Map> page = PageUtils.page(query);
        QueryWrapper wrapper = Wrappers.query();
        String dbName = query.getDbName();
        if (StringUtils.isEmpty(dbName)) {
            dbName = mapper.queryCurrenDBName();
        }
        wrapper.eq("TABLE_SCHEMA", dbName);
        if (!StringUtils.isEmpty(query.getName())) {
            wrapper.like("TABLE_NAME", query.getName());
        }
        String[] createdAt = query.getCreatedAt();
        if (createdAt != null && createdAt.length > 1) {
            wrapper.between("CREATE_TIME", createdAt[0], createdAt[1]);
        }
        return mapper.listTableByPage(page, wrapper);
    }

    @Override
    public IPage<Map> listColumnByPage(DbQuery query) {
        IPage<Map> page = PageUtils.page(query);
        QueryWrapper wrapper = Wrappers.query();
        String dbName = query.getDbName();
        if (StringUtils.isEmpty(dbName)) {
            dbName = mapper.queryCurrenDBName();
        }
        wrapper.eq("TABLE_SCHEMA", dbName);
        if (!StringUtils.isEmpty(query.getName())) {
            wrapper.eq("TABLE_NAME", query.getName());
        }
        return mapper.listColumnByPage(page, wrapper);
    }

    @Override
    public IPage<Map> listTableDataByPage(DbQuery query) {
        IPage<Map> page = PageUtils.page(query);
        QueryWrapper wrapper = Wrappers.query();
        String dbName = query.getDbName();
        if (StringUtils.isEmpty(dbName)) {
            dbName = mapper.queryCurrenDBName();
        }
        return mapper.listTableDataByPage(page, dbName + "." + query.getName(), wrapper);
    }
}
