package com.itfenbao.gadmins.admin.service;

import java.sql.SQLException;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/14 8:35 下午
 */
public interface InitService {
    boolean isInit();
    boolean init() throws SQLException;
}
