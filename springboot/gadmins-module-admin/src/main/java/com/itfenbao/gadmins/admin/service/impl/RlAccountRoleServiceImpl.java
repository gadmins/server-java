package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.entity.RlAccountRole;
import com.itfenbao.gadmins.admin.mapper.RlAccountRoleMapper;
import com.itfenbao.gadmins.admin.service.IRlAccountRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统账号角色关联表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-23
 */
@Service
public class RlAccountRoleServiceImpl extends ServiceImpl<RlAccountRoleMapper, RlAccountRole> implements IRlAccountRoleService {

    @Override
    public List<Integer> getRoleIdsByAccountId(Integer accountId) {
        return this.baseMapper.getRoleIdsByAccountId(accountId);
    }

    @Override
    public boolean removeByRoleIds(List<Integer> roleIds) {
        return remove(Wrappers.<RlAccountRole>lambdaQuery().in(RlAccountRole::getRoleId, roleIds));
    }
}
