package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Account findByNameAndPassword(String userName, String password) {
        LambdaQueryWrapper<Account> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Account::getName, userName).eq(Account::getPassword, password);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<AccountVO> getListByPage(AccountQuery query) {
        Page<AccountVO> page = new Page<>(query.getCurrent(), query.getPageSize());
        QueryWrapper<Account> wrapper = Wrappers.query();
        wrapper.eq("_account.enable", 1);
        if (!StringUtils.isEmpty(query.getName())) {
            wrapper.like("_account.name", query.getName());
        }
        if (query.getRoleId() > -1) {
            wrapper.eq("_role.id", query.getRoleId());
        }
        // 只查询非管理员账号
        wrapper.eq("_role.super_admin", 0);
        wrapper.groupBy("_account.id");
        return this.baseMapper.getListByPage(page, wrapper);
    }

    @Override
    public void updateAccount(Integer id, UpdateAccountParam param) {
        // 更新account
        Account account = new Account();
        account.setId(id);
        account.setName(param.getName());
        this.updateById(account);

        // 更新账号角色
        this.baseMapper.realDelete(Wrappers.<RlAccountRole>lambdaQuery().eq(RlAccountRole::getAccountId, id));
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
}
