package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itfenbao.gadmins.admin.data.dto.query.DbQuery;

import java.util.Map;

public interface IDbService {

    IPage<Map> listTableByPage(DbQuery pageQuery);

    IPage<Map> listColumnByPage(DbQuery query);

    IPage<Map> listTableDataByPage(DbQuery query);
}
