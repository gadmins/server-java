package com.itfenbao.gadmins.admin.component;

import com.itfenbao.gadmins.admin.data.vo.AuthFunciontVO;
import com.itfenbao.gadmins.admin.service.IAccountService;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IRlAccountRoleService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import com.itfenbao.gadmins.core.web.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        String id = TokenUtils.getUniqueIdFromToken();
        Integer accountId = Integer.parseInt(id);
        boolean isSuperAdmin = accountService.isSuperAdmin(accountId);
        if (isSuperAdmin) {
            return true;
        }
        List<Integer> roleIds = accountRoleService.getRoleIdsByAccountId(accountId);
        List<AuthFunciontVO> funciontVOS = this.functionService.getAuthFunciontVOS(roleIds);
        return funciontVOS.stream().anyMatch(i -> i.getCode().equals(authCode));
    }
}
