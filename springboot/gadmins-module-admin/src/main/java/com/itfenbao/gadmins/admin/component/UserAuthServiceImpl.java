package com.itfenbao.gadmins.admin.component;

import com.itfenbao.gadmins.admin.data.vo.AuthFunciontVO;
import com.itfenbao.gadmins.admin.service.IAccountService;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IRlAccountRoleService;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import com.itfenbao.gadmins.core.web.service.IUserAuthService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAuthServiceImpl implements IUserAuthService {

    final IAccountService accountService;
    final IRlAccountRoleService accountRoleService;
    final IFunctionService functionService;

    public UserAuthServiceImpl(IAccountService accountService, IRlAccountRoleService accountRoleService, IFunctionService functionService) {
        this.accountService = accountService;
        this.accountRoleService = accountRoleService;
        this.functionService = functionService;
    }

    @Override
    public boolean hasAuth(String authCode) {
        String id = TokenUtils.getUniqueIdFromToken();
        Integer accountId = Integer.parseInt(id);
        boolean isSuperAdmin = accountService.isSuperAdmin(accountId);
        if (isSuperAdmin) {
            return true;
        }
        List<Integer> roleIds = accountRoleService.getRoleIdsByAccountId(accountId);
        List<AuthFunciontVO> functionVOS = this.functionService.getAuthFunciontVOS(roleIds);
        return functionVOS.stream().anyMatch(i -> i.getCode().equals(authCode));
    }
}
