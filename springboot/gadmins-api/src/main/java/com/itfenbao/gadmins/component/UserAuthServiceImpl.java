package com.itfenbao.gadmins.component;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.admin.data.vo.AuthFunciontVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.admin.service.IAccountService;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IRlAccountRoleService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import com.itfenbao.gadmins.core.web.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAuthServiceImpl implements IUserAuthService {
    @Autowired
    IAccountService accountService;

    @Autowired
    IRlAccountRoleService accountRoleService;

    @Autowired
    IFunctionService functionService;

    @Override
    public boolean hasAuth(String authCode) {
        String id = TokenUtils.getUniqueIdFromToken(AppConfig.TokenType.ADMIN);
        Integer accountId = Integer.parseInt(id);
        boolean isSuperAdmin = accountService.isSuperAdmin(accountId);
        if (isSuperAdmin) {
            return true;
        }
        List<Integer> roleIds = accountRoleService.getRoleIdsByAccountId(accountId);
        List<AuthFunciontVO> funciontVOS = this.functionService.getFunctionsByRoles(roleIds);
        // 获取-vm的实际功能（即-vm的pid）
        List<Integer> vmIds = funciontVOS.stream().filter(i -> i.getCode().endsWith("-vm")).map(i -> i.getId()).collect(Collectors.toList());
        List<Integer> realIds = this.functionService.list(Wrappers.<Function>lambdaQuery().in(Function::getId, vmIds))
                .stream().map(i -> i.getPId()).collect(Collectors.toList());
        List<AuthFunciontVO> realAuths = this.functionService.list(Wrappers.<Function>lambdaQuery().in(Function::getId, realIds))
                .stream().map(i -> {
                    AuthFunciontVO funciontVO = new AuthFunciontVO();
                    funciontVO.setId(i.getId());
                    funciontVO.setCode(i.getFuncCode());
                    return funciontVO;
                }).collect(Collectors.toList());
        // 去重添加
        funciontVOS = CollUtil.addAllIfNotContains(funciontVOS, realAuths);
        return funciontVOS.stream().anyMatch(i -> i.getCode().equals(authCode));
    }
}
