package com.itfenbao.gadmins.admin.service.impl;

import com.itfenbao.gadmins.admin.entity.Account;
import com.itfenbao.gadmins.admin.service.IAccountService;
import com.itfenbao.gadmins.core.auth.token.IAccountLockService;
import org.springframework.stereotype.Service;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/7 5:04 上午
 */
@Service
public class AccountLockServiceImpl implements IAccountLockService {

    IAccountService accountService;

    public AccountLockServiceImpl(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean hasLock(Integer accountId) {
        Account account = this.accountService.getById(accountId);
        return account == null ? false : account.getLock();
    }
}
