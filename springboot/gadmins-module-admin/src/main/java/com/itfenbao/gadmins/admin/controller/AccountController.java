package com.itfenbao.gadmins.admin.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.admin.data.dto.param.LoginParam;
import com.itfenbao.gadmins.admin.data.dto.param.account.AddAccountParam;
import com.itfenbao.gadmins.admin.data.dto.param.account.ModifyPwdParam;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;


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
@RequestMapping(AppConfig.AdminRoute.ACCOUNT)
@Api(tags = "系统账号", hidden = AppConfig.HIDDEN_SYS_API)
@Menu(value = "account", parentCode = AppConfig.SysNavMenu.BASE_MGR, sort = 2, title = "账户管理", desc = "系统账户管理", url = "/system/account")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IRlAccountRoleService userRoleService;

    @Autowired
    IMenuService menuService;

    @MenuFunction(value = "sys:account:list", title = "查询", desc = "查询账号")
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

    @Function(
            value = "sys:account:unlock", sort = 5,
            title = "批量解锁", desc = "解锁账户",
            btnGroup = Function.BtnGroup.TOOLBAR
    )
    @PutMapping("/unlock/{ids}")
    @ApiOperation("解锁账号")
    public JsonResult unlock(@PathVariable List<Integer> ids) {
        List<Account> accounts = ids.stream().map(it -> {
            Account account = new Account();
            account.setId(it);
            account.setVaildErrorTimes(null);
            account.setLock(false);
            return account;
        }).collect(Collectors.toList());
        accountService.updateBatchById(accounts);
        return JsonResult.success();
    }

    /**
     * 获取当前用户的菜单
     *
     * @return
     */
    @GetMapping("/menu")
    @ApiOperation("获取当前用户菜单")
    public JsonResult<CoreMenuData> menu() {
        String id = TokenUtils.getUniqueIdFromToken();
        return JsonResult.success(menuService.getCoreMenuData(Integer.parseInt(id)));
    }

    private final static int MAX_LOGIN_ACTION = 5;

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
            Account account = this.accountService.getOne(Wrappers.<Account>lambdaQuery().eq(Account::getName, login.getUserName()));
            if (account != null) {
                if (account.getLock()) {
                    return JsonResult.paramsErrorMessage("账户已锁定，请联系管理员");
                }
                // 密码错误
                if (!login.getPassword().equals(account.getPassword())) {
                    if (accountService.isSuperAdmin(account.getId())) {
                        return JsonResult.paramsErrorMessage("密码错误");
                    }
                    if (account.getVaildErrorTimes() != null) {
                        account.setVaildErrorTimes(account.getVaildErrorTimes() + 1);
                        if (account.getVaildErrorTimes() >= MAX_LOGIN_ACTION) {
                            account.setLock(true);
                            this.accountService.updateById(account);
                            return JsonResult.paramsErrorMessage("账户已锁定，请联系管理员");
                        }
                    } else {
                        account.setVaildErrorTimes(1);
                    }
                    this.accountService.updateById(account);
                    return JsonResult.paramsErrorMessage("密码错误，还剩" + (MAX_LOGIN_ACTION - account.getVaildErrorTimes()) + "次输入机会");
                }
                String token = TokenUtils.createToken(AppConfig.TokenType.ADMIN, account.getId() + "");
                boolean isCookie = TokenUtils.isCookie(AppConfig.TokenType.ADMIN);
                account.setVaildErrorTimes(null);
                this.accountService.updateById(account);
                if (isCookie) {
                    TokenUtils.setTokenCookie(AppConfig.TokenType.ADMIN, token, response);
                    return JsonResult.success("登录成功");
                } else {
                    return JsonResult.success("登录成功", new TokenVO(token));
                }
            } else {
                return JsonResult.paramsErrorMessage("用户不存在");
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
        String id = TokenUtils.getUniqueIdFromToken();
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
        String token = TokenUtils.getToken();
        TokenUtils.removeToken(token);
        return JsonResult.success("登出成功");
    }

    @PutMapping("/modifyPwd")
    @ApiOperation("修改密码")
    public JsonResult modifyPwd(@RequestBody ModifyPwdParam param) {
        String id = TokenUtils.getUniqueIdFromToken();
        Account account = accountService.getById(id);
        if (account == null) {
            return JsonResult.failMessage("用户不存在不存在");
        }
        if (!account.getPassword().equals(param.getOldPwd())) {
            return JsonResult.failMessage("原密码错误");
        }
        LambdaUpdateWrapper<Account> updateWrapper = Wrappers.<Account>lambdaUpdate();
        updateWrapper.set(Account::getPassword, param.getNewPwd());
        updateWrapper.eq(Account::getId, Integer.parseInt(id));
        accountService.update(updateWrapper);
        return JsonResult.success("修改成功");
    }
}
