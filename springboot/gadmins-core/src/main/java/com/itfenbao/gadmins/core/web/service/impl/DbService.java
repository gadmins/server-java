package com.itfenbao.gadmins.core.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.core.utils.PageUtils;
import com.itfenbao.gadmins.core.web.data.dto.param.db.AddTableParam;
import com.itfenbao.gadmins.core.web.data.dto.param.db.UpdateTableParam;
import com.itfenbao.gadmins.core.web.data.dto.query.DbTableQuery;
import com.itfenbao.gadmins.core.web.data.dto.query.TableColumnQuery;
import com.itfenbao.gadmins.core.web.mapper.DbMapper;
import com.itfenbao.gadmins.core.web.service.IDbService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DbService implements IDbService {

    DbMapper mapper;

    public DbService(DbMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public IPage<Map> listTableByPage(DbTableQuery query) {
        IPage<Map> page = PageUtils.page(query);
        QueryWrapper wrapper = Wrappers.query();
        String dbName = query.getDbName();
        if (StringUtils.isEmpty(dbName)) {
            dbName = mapper.queryCurrenDBName();
        }
        wrapper.eq("TABLE_SCHEMA", dbName);
        if (StringUtils.isNotBlank(query.getTableName())) {
            wrapper.like("TABLE_NAME", query.getTableName());
        }
        if (StringUtils.isNotBlank(query.getComment())) {
            wrapper.like("TABLE_COMMENT", query.getComment());
        }
        String[] createdAt = query.getCreatedAt();
        if (createdAt != null && createdAt.length > 1) {
            wrapper.between("CREATE_TIME", createdAt[0], createdAt[1]);
        }
        return mapper.listTableByPage(page, wrapper);
    }

    @Override
    public IPage<Map> listColumnByPage(TableColumnQuery query) {
        IPage<Map> page = PageUtils.page(query);
        QueryWrapper wrapper = Wrappers.query();
        String dbName = query.getDbName();
        if (StringUtils.isBlank(dbName)) {
            dbName = mapper.queryCurrenDBName();
        }
        wrapper.eq("TABLE_SCHEMA", dbName);
        if (StringUtils.isNotBlank(query.getTableName())) {
            wrapper.eq("TABLE_NAME", query.getTableName());
        }
        if (StringUtils.isNotBlank(query.getColumnName())) {
            wrapper.like("COLUMN_NAME", query.getColumnName());
        }
        return mapper.listColumnByPage(page, wrapper);
    }

    @Override
    public IPage<Map> listTableDataByPage(DbTableQuery query) {
        IPage<Map> page = PageUtils.page(query);
        QueryWrapper wrapper = Wrappers.query();
        String dbName = query.getTableName();
        if (StringUtils.isBlank(dbName)) {
            dbName = mapper.queryCurrenDBName();
        }
        return mapper.listTableDataByPage(page, dbName + "." + query.getTableName(), wrapper);
    }

    @Override
    public boolean createTable(AddTableParam param) {
        if (mapper.isTableExist(param.getName()) > 0) {
            return false;
        }
        mapper.createTable(param.getName(), param.getComment(), param.isHasDelete());
        return true;
    }

    @Override
    public boolean dropTable(String name) {
        if (mapper.isTableExist(name) > 0) {
            mapper.dropTable(name);
            return true;
        }
        return false;
    }

    @Override
    public boolean truncateTable(String name) {
        if (mapper.isTableExist(name) > 0) {
            mapper.truncateTable(name);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTable(String name, UpdateTableParam param) {
        boolean has = mapper.isTableExist(name) > 0;
        if (has) {
            mapper.renameTableComment(name, param.getComment());
        }
        if (has && !name.equals(param.getNewName())) {
            mapper.renameTableName(name, param.getNewName());
        }
        return true;
    }


}
