package com.itfenbao.gadmins.admin.mapper;

import com.itfenbao.gadmins.admin.entity.RlAccountRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统账号角色关联表 Mapper 接口
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-23
 */
public interface RlAccountRoleMapper extends BaseMapper<RlAccountRole> {
    List<Integer> getRoleIdsByAccountId(Integer accountId);
}
