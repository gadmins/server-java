package com.itfenbao.gadmins.app.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.app.entity.Role;
import com.itfenbao.gadmins.app.mapper.RoleMapper;
import com.itfenbao.gadmins.app.service.IRoleService;
import com.itfenbao.gadmins.core.web.query.PageQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> getAllRoleNotSuperAdmin() {
        return this.list(Wrappers.<Role>lambdaQuery().eq(Role::getSuperAdmin, 0));
    }

    @Override
    public Page<Role> getPageListNotSuperAdmin(PageQuery query) {
        Page<Role> page = new Page<>(query.getCurrent(), query.getPageSize());
        return this.page(page, Wrappers.<Role>lambdaQuery().eq(Role::getSuperAdmin, 0));
    }

}
