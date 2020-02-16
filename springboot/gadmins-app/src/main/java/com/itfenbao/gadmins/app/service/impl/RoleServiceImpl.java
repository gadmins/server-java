package com.itfenbao.gadmins.app.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.app.entity.Role;
import com.itfenbao.gadmins.app.mapper.RoleMapper;
import com.itfenbao.gadmins.app.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public List<Role> getListNotSuperAdmin() {
        return this.list(Wrappers.<Role>lambdaQuery().eq(Role::getSuperAdmin, 0));
    }
}
