package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.dto.query.AccoutQuery;
import com.itfenbao.gadmins.admin.data.vo.AccoutVO;
import com.itfenbao.gadmins.admin.entity.Accout;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-16
 */
public interface IAccoutService extends IService<Accout> {

    /**
     * 通过用户名和密码查询
     *
     * @param userName
     * @param password
     * @return
     */
    Accout findByNameAndPassword(String userName, String password);

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<AccoutVO> getListByPage(AccoutQuery query);
}
