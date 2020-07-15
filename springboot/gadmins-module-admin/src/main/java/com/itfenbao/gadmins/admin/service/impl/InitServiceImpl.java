package com.itfenbao.gadmins.admin.service.impl;

import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.admin.service.InitService;
import com.itfenbao.gadmins.core.utils.SpringBootUtils;
import com.itfenbao.gadmins.core.web.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/14 8:41 下午
 */
@Service
public class InitServiceImpl implements InitService {

    private final String CREATE_TABLES_SQL = "classpath:sql/gadmins.sql";
    private final String INIT_DATA_SQL = "classpath:sql/gadmins-init-data.sql";
    private final String MENU_INIT_SQL = "classpath:sql/menu_init_data.sql";

    private DataSource datasource;

    private IMenuService menuService;

    private DbMapper dbMapper;

    @Autowired
    public void setDbMapper(DbMapper dbMapper) {
        this.dbMapper = dbMapper;
    }

    @Autowired
    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

    @Autowired
    public void setMenuService(IMenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public boolean isInit() {
        return dbMapper.countTable() > 0;
    }

    @Override
    public boolean init() throws SQLException {
        if (isInit()) {
            return false;
        }
        SpringBootUtils.executeSqlScript(datasource, CREATE_TABLES_SQL);
        SpringBootUtils.executeSqlScript(datasource, INIT_DATA_SQL);
        SpringBootUtils.executeSqlScript(datasource, MENU_INIT_SQL);
        menuService.updateScanMenus();
        return true;
    }

}
