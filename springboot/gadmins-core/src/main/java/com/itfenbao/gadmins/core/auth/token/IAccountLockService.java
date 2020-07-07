package com.itfenbao.gadmins.core.auth.token;

/**
 * @author itfenbao
 * @version 1.0
 * @description: 用户锁定服务
 * @date :2020/7/7 4:54 上午
 */
public interface IAccountLockService {
    boolean hasLock(Integer accountId);
}
