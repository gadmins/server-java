package com.itfenbao.gadmins.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.app.entity.Role;
import com.itfenbao.gadmins.core.web.query.PageQuery;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询非超管所有角色
     * @return
     */
    List<Role> getAllRoleNotSuperAdmin();

    /**
     * 分页查询非超管角色
     * @param query
     * @return
     */
    Page<Role> getPageListNotSuperAdmin(PageQuery query);
}
