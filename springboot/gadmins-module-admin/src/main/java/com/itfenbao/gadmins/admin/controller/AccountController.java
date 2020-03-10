package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import com.itfenbao.gadmins.core.annotation.*;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import com.itfenbao.gadmins.core.web.result.JsonPageResult;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import com.itfenbao.gadmins.core.web.vo.TokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Menu(value = "account", parentCode = AppConfig.SysNavMenu.BASE_MGR, sort = 2, title = "账户管理", desc = "系统账户管理", url = "/system/account")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IRlAccountRoleService userRoleService;

    @Autowired
    IMenuService menuService;

    @Function(value = "sys:account:list", sort = 0, title = "查询", desc = "查询账号", menu = true)
    @GetMapping()
    @ApiOperation(value = "账号查询")
    @Schema(AccountVO.class)
    public JsonPageResult<AccountVO> list(final AccountQuery query) {
        final Page<AccountVO> page = accountService.getListByPage(query);
        return JsonPageResult.success(page);
    }


    @Functions({
            @Function(value = "sys:account:add", sort = 1, title = "新增", desc = "新增账户", icon = "plus", btnGroup = Function.BtnGroup.TOOLBAR),
            @Function(value = "sys:account:copy", sort = 3, title = "复制", desc = "复制账户", icon = "plus")
    })
    @PostMapping
    @ApiOperation("添加账号")
    public JsonResult create(@RequestBody AddAccountParam param) {
        if (accountService.count(Wrappers.<Account>lambdaQuery().eq(Account::getName, param.getName())) > 0) {
            return JsonResult.failMessage("账户名已存在");
        }
        Account account = new Account();
        account.setName(param.getName());
        account.setPassword(param.getPassword());
        accountService.save(account);
        if (account.getId() != null) {
            param.getRoles().forEach(roleId -> {
                RlAccountRole userRole = new RlAccountRole();
                userRole.setAccountId(account.getId());
                userRole.setRoleId(roleId);
                userRoleService.save(userRole);
            });
        }
        return JsonResult.success();
    }

    @Function(
            value = "sys:account:edit", sort = 2,
            title = "编辑", desc = "编辑账户"
    )
    @PutMapping("/{id}")
    @ApiOperation("修改账号")
    public JsonResult update(@PathVariable("id") Integer id, @RequestBody UpdateAccountParam param) {
        accountService.updateAccount(id, param);
        return JsonResult.success();
    }

    @Function(
            value = "sys:account:del", sort = 4,
            title = "批量删除", desc = "删除账户",
            btnGroup = Function.BtnGroup.TOOLBAR
    )
    @DeleteMapping("/{ids}")
    @ApiOperation("删除账号")
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
    @ApiOperation("获取当前用户菜单")
    public JsonResult<CoreMenuData> menu(HttpServletRequest request) {
        String id = TokenUtils.getUniqueIdFromToken(AppConfig.TokenType.ADMIN);
        return JsonResult.success(menuService.getCoreMenuData(Integer.parseInt(id)));
    }

    @PostMapping("/login")
    @PassToken
    @ApiOperation("登录")
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
    @ApiOperation("获取登陆验证码")
    public JsonResult captcha(@RequestParam("mobile") final String mobile) {
        return JsonResult.failMessage("暂不支持");
    }

    @GetMapping("/currentAccount")
    @ApiOperation("获取当前用户信息")
    public JsonResult<Account> currentAccount() {
        String id = TokenUtils.getUniqueIdFromToken(AppConfig.TokenType.ADMIN);
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
    @ApiOperation("账号退出")
    public JsonResult logout() {
        String token = TokenUtils.getToken(AppConfig.TokenType.ADMIN);
        TokenUtils.removeToken(AppConfig.TokenType.ADMIN, token);
        return JsonResult.success("登出成功");
    }
}
