package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.admin.data.dto.param.LoginParam;
import com.itfenbao.gadmins.admin.data.dto.param.accout.AddAccoutParam;
import com.itfenbao.gadmins.admin.data.dto.param.accout.UpdateAccoutParam;
import com.itfenbao.gadmins.admin.data.dto.query.AccoutQuery;
import com.itfenbao.gadmins.admin.data.vo.AccoutVO;
import com.itfenbao.gadmins.admin.data.vo.CoreMenuData;
import com.itfenbao.gadmins.admin.entity.Accout;
import com.itfenbao.gadmins.admin.entity.RlAccoutRole;
import com.itfenbao.gadmins.admin.service.IAccoutService;
import com.itfenbao.gadmins.admin.service.IMenuService;
import com.itfenbao.gadmins.admin.service.IRlAccoutRoleService;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.PassToken;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.PageData;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(AppConfig.AdminRoute.ADMIN_ACCOUT)
@Api(tags = "系统账号")
public class AccoutController {

    @Autowired
    IAccoutService accoutService;

    @Autowired
    IRlAccoutRoleService userRoleService;

    @Autowired
    IMenuService menuService;

    @Function("sys.accout.list")
    @GetMapping()
    public JsonResult<PageData<AccoutVO>> list(final AccoutQuery query) {
        final Page<AccoutVO> page = accoutService.getListByPage(query);
        return JsonResult.success(PageData.get(page));
    }

    @PostMapping()
    public JsonResult create(@RequestBody AddAccoutParam param) {
        Accout accout = new Accout();
        accout.setName(param.getName());
        accout.setPassword(param.getPassword());
        accoutService.save(accout);
        if (accout.getId() != null) {
            param.getRoles().forEach(roleId -> {
                RlAccoutRole userRole = new RlAccoutRole();
                userRole.setUserId(accout.getId());
                userRole.setRoleId(roleId);
                userRoleService.save(userRole);
            });
        }
        return JsonResult.success();
    }

    @PutMapping("/{id}")
    public JsonResult update(@PathVariable("id") Integer id, @RequestBody UpdateAccoutParam param) {
        accoutService.updateAccout(id, param);
        return JsonResult.success();
    }

    @DeleteMapping("/{ids}")
    public JsonResult deletes(@PathVariable() List<Integer> ids) {
        accoutService.removeByIds(ids);
        return JsonResult.success();
    }

    @GetMapping("/menu")
    public JsonResult<CoreMenuData> menu() {
        return JsonResult.success(menuService.getCoreMenuData());
    }

    @PostMapping("/login")
    @PassToken
    public JsonResult login(@RequestBody @Validated final LoginParam login) {
        if (LoginParam.LOGIN_TYPE_ACCOUNT.equals(login.getType().toLowerCase())) {
            if (StringUtils.isEmpty(login.getUserName())) {
                return JsonResult.paramsErrorMessage("用户名不能为空");
            }
            if (StringUtils.isEmpty(login.getPassword())) {
                return JsonResult.paramsErrorMessage("密码不能为空");
            }
            final Accout accout = this.accoutService.findByNameAndPassword(login.getUserName(), login.getPassword());
            return accout != null ? JsonResult.success("登录成功") : JsonResult.paramsErrorMessage("用户名和密码错误");
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

    @GetMapping("/login/captcha")
    @PassToken
    public JsonResult captcha(@RequestParam("mobile") final String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return JsonResult.paramsErrorMessage("手机号不能为空");
        }
        return JsonResult.success("发送成功", "1234");
    }

    @GetMapping("/currentAccout")
    public JsonResult<Accout> currentUser() {
        final Accout user = new Accout();
        user.setName("admin");
        return JsonResult.success(user);
    }

//    @PostMapping("/logout")
//    @PassToken
//    public JsonResult logout() {
//
//    }
}
