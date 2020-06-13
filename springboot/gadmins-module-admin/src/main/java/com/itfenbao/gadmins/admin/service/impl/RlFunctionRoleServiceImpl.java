package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.entity.RlFunctionRole;
import com.itfenbao.gadmins.admin.mapper.RlFunctionRoleMapper;
import com.itfenbao.gadmins.admin.service.IRlFunctionRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色功能关联表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-23
 */
@Service
public class RlFunctionRoleServiceImpl extends ServiceImpl<RlFunctionRoleMapper, RlFunctionRole> implements IRlFunctionRoleService {
    @Override
    public boolean removeByRoleIds(List<Integer> roleIds) {
        return remove(Wrappers.<RlFunctionRole>lambdaQuery().in(RlFunctionRole::getRoleId, roleIds));
    }
}
