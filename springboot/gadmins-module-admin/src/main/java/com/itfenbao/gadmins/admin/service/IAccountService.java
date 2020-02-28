package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.dto.param.account.UpdateAccountParam;
import com.itfenbao.gadmins.admin.data.dto.query.AccountQuery;
import com.itfenbao.gadmins.admin.data.vo.AccountVO;
import com.itfenbao.gadmins.admin.entity.Account;

/**
 * <p>
 * 系统账号表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-16
 */
public interface IAccountService extends IService<Account> {

    /**
     * 通过用户名和密码查询
     *
     * @param userName
     * @param password
     * @return
     */
    Account findByNameAndPassword(String userName, String password);

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<AccountVO> getListByPage(AccountQuery query);

    /**
     * 更新用户
     * @param param
     * @return
     */
    void updateAccount(Integer id, UpdateAccountParam param);

    /**
     * 判断用户是否是超管
     * @param id
     * @return
     */
    boolean isSuperAdmin(Integer id);
}
