package com.itfenbao.gadmins.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.admin.data.vo.AccountVO;
import com.itfenbao.gadmins.admin.entity.Account;
import com.itfenbao.gadmins.admin.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统账号表 Mapper 接口
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-16
 */
public interface AccountMapper extends BaseMapper<Account> {

    Page<AccountVO> getListByPage(Page<AccountVO> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 单表分页查询（不能通过角色等查询）
     * @param page
     * @param wrapper
     * @return
     */
    Page<AccountVO> listAccoutByPage(Page<AccountVO> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    List<Role> getRoles(Integer userId);

    int countSuperAdmin(Integer userId);

    int querySuperAdminId();
}
