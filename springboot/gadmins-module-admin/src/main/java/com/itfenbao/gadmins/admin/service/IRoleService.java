package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.dto.query.RoleQuery;
import com.itfenbao.gadmins.admin.entity.Role;

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
     *
     * @return
     */
    List<Role> getAllRoleNotSuperAdmin();

    /**
     * 分页查询非超管角色
     *
     * @param query
     * @return
     */
    Page<Role> getPageListNotSuperAdmin(RoleQuery query);

}
