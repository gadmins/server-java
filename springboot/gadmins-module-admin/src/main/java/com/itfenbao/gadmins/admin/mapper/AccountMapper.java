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

    List<Role> getRoles(Integer userId);

    /**
     * 真实删除
     *
     * @param wrapper
     * @return
     */
    boolean realDelete(@Param(Constants.WRAPPER) Wrapper wrapper);

    int countSuperAdmin(Integer userId);
}
