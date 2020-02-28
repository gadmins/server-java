package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.entity.RlAccountRole;

import java.util.List;

/**
 * <p>
 * 系统账号角色关联表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-23
 */
public interface IRlAccountRoleService extends IService<RlAccountRole> {
    /**
     * 获取用户角色ids
     * @param accountId
     * @return
     */
    List<Integer> getRoleIdsByAccountId(Integer accountId);
}
