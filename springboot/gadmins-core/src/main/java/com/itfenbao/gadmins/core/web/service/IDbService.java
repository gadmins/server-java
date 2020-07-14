package com.itfenbao.gadmins.core.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itfenbao.gadmins.core.web.data.dto.param.db.*;
import com.itfenbao.gadmins.core.web.data.dto.query.db.DbTableQuery;
import com.itfenbao.gadmins.core.web.data.dto.query.db.TableColumnQuery;
import com.itfenbao.gadmins.core.web.data.dto.query.db.TableDataQuery;

import java.util.Map;

public interface IDbService {

    int countTable();

    IPage<Map> listTableByPage(DbTableQuery pageQuery);

    IPage<Map> listColumnByPage(TableColumnQuery query);

    IPage<Map> listTableDataByPage(TableDataQuery query);

    boolean createTable(AddTableParam param);

    boolean dropTable(String name);

    boolean truncateTable(String name);

    boolean updateTable(String name, UpdateTableParam param);

    boolean addColumn(AddColumnParam param);

    boolean deleteColumn(DeleteColumnParam param);

    boolean updateColumn(UpdateColumnParam param);
}
