package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.admin.data.dto.param.LoginParam;
import com.itfenbao.gadmins.admin.data.dto.param.account.AddAccountParam;
import com.itfenbao.gadmins.admin.data.dto.param.account.UpdateAccountParam;
import com.itfenbao.gadmins.admin.data.dto.query.AccountQuery;
import com.itfenbao.gadmins.admin.data.vo.AccountVO;
import com.itfenbao.gadmins.admin.data.vo.CoreMenuData;
import com.itfenbao.gadmins.admin.entity.Account;
import com.itfenbao.gadmins.admin.entity.RlAccountRole;
import com.itfenbao.gadmins.admin.service.IAccountService;
import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.admin.service.IRlAccountRoleService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.annotation.PassToken;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.PageData;
import com.itfenbao.gadmins.core.web.vo.TokenVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 系统账号表 前端控制器
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-16
 */
@Slf4j
@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_ACCOUNT)
@Api(tags = "系统账号")
@Menu(value = "sys.account", title = "账户管理", desc = "系统账户管理")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IRlAccountRoleService userRoleService;

    @Autowired
    IMenuService menuService;

    @Function(value = "sys:account:list", title = "账号查询")
    @GetMapping()
    public JsonResult<PageData<AccountVO>> list(final AccountQuery query) {
        final Page<AccountVO> page = accountService.getListByPage(query);
        return JsonResult.success(PageData.get(page));
    }

    @Function(
            value = "sys:account:add",
            parentCode = "sys:account:list",
            title = "新增", desc = "新增账户", icon = "plus",
            btnGroup = Function.BtnGroup.TOOLBAR
    )
    @PostMapping()
    public JsonResult create(@RequestBody AddAccountParam param) {
        Account account = new Account();
        account.setName(param.getName());
        account.setPassword(param.getPassword());
        accountService.save(account);
        if (account.getId() != null) {
            param.getRoles().forEach(roleId -> {
                RlAccountRole userRole = new RlAccountRole();
                userRole.setUserId(account.getId());
                userRole.setRoleId(roleId);
                userRoleService.save(userRole);
            });
        }
        return JsonResult.success();
    }

    @Function(
            value = "sys:account:edit",
            parentCode = "sys:account:list",
            title = "编辑", desc = "编辑账户",
            btnGroup = Function.BtnGroup.OP
    )
    @PutMapping("/{id}")
    public JsonResult update(@PathVariable("id") Integer id, @RequestBody UpdateAccountParam param) {
        accountService.updateAccount(id, param);
        return JsonResult.success();
    }

    @Function(
            value = "sys:account:del",
            parentCode = "sys:account:list",
            title = "批量删除", desc = "删除账户",
            btnGroup = Function.BtnGroup.OP
    )
    @DeleteMapping("/{ids}")
    public JsonResult deletes(@PathVariable List<Integer> ids) {
        accountService.removeByIds(ids);
        return JsonResult.success();
    }

    /**
     * 获取当前用户的菜单
     *
     * @param request
     * @return
     */
    @GetMapping("/menu")
    public JsonResult<CoreMenuData> menu(HttpServletRequest request) {
        String id = TokenUtils.getUniqueIdFromToken(AppConfig.TokenType.ADMIN, request);
        return JsonResult.success(menuService.getCoreMenuData(Integer.parseInt(id)));
    }

    @PostMapping("/login")
    @PassToken
    public JsonResult login(@RequestBody @Validated final LoginParam login, HttpServletResponse response) {
        if (LoginParam.LOGIN_TYPE_ACCOUNT.equals(login.getType().toLowerCase())) {
            if (StringUtils.isEmpty(login.getUserName())) {
                return JsonResult.paramsErrorMessage("用户名不能为空");
            }
            if (StringUtils.isEmpty(login.getPassword())) {
                return JsonResult.paramsErrorMessage("密码不能为空");
            }
            final Account account = this.accountService.findByNameAndPassword(login.getUserName(), login.getPassword());
            if (account != null) {
                String token = TokenUtils.createToken(AppConfig.TokenType.ADMIN, account.getId() + "");
                boolean isCookie = TokenUtils.isCookie(AppConfig.TokenType.ADMIN);
                if (isCookie) {
                    TokenUtils.setTokenCookie(AppConfig.TokenType.ADMIN, token, response);
                    return JsonResult.success("登录成功");
                } else {
                    return JsonResult.success("登录成功", new TokenVO(token));
                }
            } else {
                return JsonResult.paramsErrorMessage("用户名和密码错误");
            }
        } else if (LoginParam.LOGIN_TYPE_MOBILE.equals(login.getType().toLowerCase())) {
            if (StringUtils.isEmpty(login.getMobile())) {
                return JsonResult.paramsErrorMessage("手机号不能为空");
            }
            if (StringUtils.isEmpty(login.getCaptcha())) {
                return JsonResult.paramsErrorMessage("验证码不能为空");
            }
            return JsonResult.paramsErrorMessage("暂未实现手机号登录");
        }
        return JsonResult.paramsErrorMessage("登录失败");
    }

    /**
     * 获取登陆验证码
     *
     * @param mobile
     * @return
     */
    @GetMapping("/login/captcha")
    @PassToken
    public JsonResult captcha(@RequestParam("mobile") final String mobile) {
        return JsonResult.failMessage("暂不支持");
    }

    @GetMapping("/currentAccount")
    public JsonResult<Account> currentAccount(HttpServletRequest request) {
        String id = TokenUtils.getUniqueIdFromToken(AppConfig.TokenType.ADMIN, request);
        Account account = accountService.getById(id);
        if (account == null) {
            return JsonResult.failMessage("用户不存在不存在");
        }
        final Account user = new Account();
        user.setName(account.getName());
        return JsonResult.success(user);
    }

    @PostMapping("/logout")
    @PassToken
    public JsonResult logout(HttpServletRequest request) {
        String token = TokenUtils.getToken(AppConfig.TokenType.ADMIN, request);
        TokenUtils.removeToken(AppConfig.TokenType.ADMIN, token);
        return JsonResult.success("登出成功");
    }
}
