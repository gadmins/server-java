package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.dto.query.RoleQuery;
import com.itfenbao.gadmins.admin.entity.Role;
import com.itfenbao.gadmins.admin.mapper.RoleMapper;
import com.itfenbao.gadmins.admin.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Page<Role> getPageListNotSuperAdmin(RoleQuery query) {
        Page<Role> page = new Page<>(query.getCurrent(), query.getPageSize());
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery().eq(Role::getSuperAdmin, 0);
        if(!StringUtils.isEmpty(query.getName())) {
            wrapper.like(Role::getName, query.getName());
        }
        String[] createdAt = query.getCreatedAt();
        if (createdAt != null && createdAt.length > 1) {
            wrapper.between(Role::getCreatedAt, createdAt[0], createdAt[1]);
        }
        return this.page(page, wrapper);
    }

}
