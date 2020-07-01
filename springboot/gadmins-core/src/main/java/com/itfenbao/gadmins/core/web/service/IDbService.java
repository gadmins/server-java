package com.itfenbao.gadmins.core.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itfenbao.gadmins.core.web.data.dto.param.db.AddTableParam;
import com.itfenbao.gadmins.core.web.data.dto.param.db.UpdateTableParam;
import com.itfenbao.gadmins.core.web.data.dto.query.DbQuery;

import java.util.Map;

public interface IDbService {

    IPage<Map> listTableByPage(DbQuery pageQuery);

    IPage<Map> listColumnByPage(DbQuery query);

    IPage<Map> listTableDataByPage(DbQuery query);

    boolean createTable(AddTableParam param);

    boolean dropTable(String name);

    boolean truncateTable(String name);

    boolean updateTable(String name, UpdateTableParam param);
}
