package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.entity.RlMenuRole;

import java.util.List;

/**
 * <p>
 * 系统角色菜单关联表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-23
 */
public interface IRlMenuRoleService extends IService<RlMenuRole> {
    /**
     * 删除角色关联
     *
     * @param ids 角色ID集合
     * @return
     */
    boolean removeByRoleIds(List<Integer> ids);
}
