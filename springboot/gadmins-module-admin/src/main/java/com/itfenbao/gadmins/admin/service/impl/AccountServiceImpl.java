package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.dto.param.account.UpdateAccountParam;
import com.itfenbao.gadmins.admin.data.dto.query.AccountQuery;
import com.itfenbao.gadmins.admin.data.vo.AccountVO;
import com.itfenbao.gadmins.admin.entity.Account;
import com.itfenbao.gadmins.admin.entity.RlAccountRole;
import com.itfenbao.gadmins.admin.mapper.AccountMapper;
import com.itfenbao.gadmins.admin.service.IAccountService;
import com.itfenbao.gadmins.admin.service.IRlAccountRoleService;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统账号表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-16
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Autowired
    IRlAccountRoleService accountRoleService;

    @Override
    public Page<AccountVO> getListByPage(AccountQuery query) {
        Page<AccountVO> page = new Page<>(query.getCurrent(), query.getPageSize());
        QueryWrapper<Account> wrapper = Wrappers.query();
        wrapper.eq("_account.enable", 1);
        if (StringUtils.isNotBlank(query.getName())) {
            wrapper.like("_account.name", query.getName());
        }
        String[] createdAt = query.getCreatedAt();
        if (createdAt != null && createdAt.length > 1) {
            wrapper.between("_account.created_at", createdAt[0], createdAt[1]);
        }
        // 是否需要连接查询
        boolean joinSelect = query.getRoleId() > -1;
        if (joinSelect) {
            wrapper.eq("_role.id", query.getRoleId());
        }
        // 过滤自己和超管
        if (joinSelect) {
            // 只查询非管理员账号
            wrapper.eq("_role.super_admin", 0);
        }
        String accountId = TokenUtils.getUniqueIdFromToken();
        if (StringUtils.isNotBlank(accountId)) {
            try {
                int id = Integer.parseInt(accountId);
                if (joinSelect) {
                    wrapper.ne("_account.id", id);
                } else {
                    wrapper.notIn("_account.id", id, getSuperAdminId());
                }
            } catch (Exception e) {
            }
        }
        wrapper.groupBy("_account.id");
        return joinSelect ? this.baseMapper.getListByPage(page, wrapper) : this.baseMapper.listAccoutByPage(page, wrapper);
    }

    @Override
    public void updateAccount(Integer id, UpdateAccountParam param) {
        // 更新account
//        Account account = new Account();
//        account.setId(id);
//        this.updateById(account);

        this.accountRoleService.remove(Wrappers.<RlAccountRole>lambdaQuery().eq(RlAccountRole::getAccountId, id));
        List<RlAccountRole> roles = new ArrayList<>();
        param.getRoles().forEach(roleId -> {
            RlAccountRole userRole = new RlAccountRole();
            userRole.setAccountId(id);
            userRole.setRoleId(roleId);
            roles.add(userRole);
        });
        accountRoleService.saveBatch(roles);
    }

    @Override
    public boolean isSuperAdmin(Integer id) {
        return this.baseMapper.countSuperAdmin(id) > 0;
    }

    @Override
    public Integer getSuperAdminId() {
        return this.baseMapper.querySuperAdminId();
    }
}
