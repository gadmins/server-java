package com.itfenbao.gadmins.admin.controller;


import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.core.AppConfig;
import com.itfenbao.gadmins.core.web.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统功能表 前端控制器
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_FUNCTION)
@Api(tags = "系统功能点")
public class FunctionController {

    @Autowired
    IFunctionService functionService;

    @GetMapping("/list")
    public JsonResult<List<FunctionVO>> list() {
        return JsonResult.success(functionService.getListByNullBtnGroup());
    }

}
