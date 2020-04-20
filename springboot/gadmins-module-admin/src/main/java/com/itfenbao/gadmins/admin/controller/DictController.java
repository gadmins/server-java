package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.admin.data.dto.param.dict.AddDictParam;
import com.itfenbao.gadmins.admin.data.dto.param.dict.UpdateDictParam;
import com.itfenbao.gadmins.admin.data.dto.query.DictQuery;
import com.itfenbao.gadmins.admin.entity.Dict;
import com.itfenbao.gadmins.admin.service.IDictService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Functions;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.annotation.Schema;
import com.itfenbao.gadmins.core.event.RefreshDictEvent;
import com.itfenbao.gadmins.core.web.result.JsonPageResult;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统字典表 前端控制器
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_DICT)
@Api(tags = "系统字典", hidden = AppConfig.HIDDEN_SYS_API)
@Menu(value = "dict", parentCode = AppConfig.SysNavMenu.BASE_MGR, sort = 4, title = "字典管理", desc = "系统字典管理", url = "/system/dict")
public class DictController {

    @Autowired
    IDictService dictService;

    @Autowired
    ApplicationContext applicationContext;

    @Function(value = "sys:dict:list", sort = 0, title = "查询", desc = "查询字典", menu = true)
    @GetMapping()
    @ApiOperation("分页查询")
    @Schema(Dict.class)
    public JsonPageResult<Dict> list(final DictQuery query) {
        return JsonPageResult.success(dictService.getListByPage(query));
    }

    @Functions({
            @Function(value = "sys:dict:add", sort = 1, title = "新建", desc = "新建字典", icon = "plus", btnGroup = Function.BtnGroup.TOOLBAR),
            @Function(value = "sys:dict:copy", sort = 3, title = "复制", desc = "复制字典"),
            @Function(value = "sys:dict:data:list:add", sort = 6, parentCode = "sys:dict:data:list", title = "新建", desc = "新建字典数据", btnGroup = Function.BtnGroup.TOOLBAR),
            @Function(value = "sys:dict:data:list:copy", sort = 9, parentCode = "sys:dict:data:list", title = "复制", desc = "复制字典数据")
    })
    @PostMapping()
    @ApiOperation("添加字典")
    public JsonResult create(@RequestBody AddDictParam param) {
        Dict dict = new Dict();
        dict.setPId(param.getPId());
        dict.setTitle(param.getTitle());
        dict.setDCode(param.getDcode());
        dict.setIndexValue(param.getIndexValue());
        dict.setDValue(param.getDvalue());
        dictService.save(dict);
        applicationContext.publishEvent(new RefreshDictEvent(this));
        return JsonResult.success();
    }

    @Functions({
            @Function(value = "sys:dict:edit", sort = 2, title = "编辑", desc = "编辑字典"),
            @Function(value = "sys:dict:data:list:edit", sort = 8, parentCode = "sys:dict:data:list", title = "编辑", desc = "编辑字典数据")
    })
    @PutMapping("/{id}")
    @ApiOperation("更新字典")
    public JsonResult update(@PathVariable Integer id, @RequestBody UpdateDictParam param) {
        Dict dict = new Dict();
        dict.setId(id);
        dict.setTitle(param.getTitle());
        dict.setDCode(param.getDcode());
        dict.setIndexValue(param.getIndexValue());
        dict.setDValue(param.getDvalue());
        dictService.updateById(dict);
        applicationContext.publishEvent(new RefreshDictEvent(this));
        return JsonResult.success();
    }

    @Functions({
            @Function(value = "sys:dict:del", sort = 4, title = "批量删除", desc = "批量删除字典", btnGroup = Function.BtnGroup.TOOLBAR),
            @Function(value = "sys:dict:data:list:del", sort = 7, parentCode = "sys:dict:data:list", title = "批量删除", desc = "批量删除字典数据", btnGroup = Function.BtnGroup.TOOLBAR)

    })
    @DeleteMapping("/{ids}")
    @ApiOperation("批量删除字典")
    public JsonResult deletes(@PathVariable List<Integer> ids) {
        dictService.removeByIds(ids);
        applicationContext.publishEvent(new RefreshDictEvent(this));
        return JsonResult.success();
    }

    @Function(value = "sys:dict:data:list", sort = 5, title = "查看字典数据", desc = "查看字典数据", url = "/system/dict/list")
    @GetMapping("/list/{pid}")
    @ApiOperation("查询字典数据")
    @Schema(Dict.class)
    public JsonPageResult<Dict> allValDict(@ApiParam(value = "字典父ID", required = true) @PathVariable Integer pid, final DictQuery query) {
        Page<Dict> page = new Page<>(query.getCurrent(), query.getPageSize());
        dictService.page(page, Wrappers.<Dict>lambdaQuery().eq(Dict::getPId, pid).orderByAsc(Dict::getIndexValue));
        return JsonPageResult.success(page);
    }

}
