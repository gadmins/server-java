package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.entity.RlMenuRole;
import com.itfenbao.gadmins.admin.mapper.RlMenuRoleMapper;
import com.itfenbao.gadmins.admin.service.IRlMenuRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色菜单关联表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-23
 */
@Service
public class RlMenuRoleServiceImpl extends ServiceImpl<RlMenuRoleMapper, RlMenuRole> implements IRlMenuRoleService {

    @Override
    public boolean removeByRoleIds(List<Integer> roleIds) {
        return remove(Wrappers.<RlMenuRole>lambdaQuery().in(RlMenuRole::getRoleId, roleIds));
    }
}
