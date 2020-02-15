package com.itfenbao.gadmins.app.service;

import com.itfenbao.gadmins.app.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
public interface IUserService extends IService<User> {

    /**
     * 通过用户名和密码查询
     * @param name
     * @param password
     * @return
     */
    User findByNameAndPassword(String name, String password);

}
