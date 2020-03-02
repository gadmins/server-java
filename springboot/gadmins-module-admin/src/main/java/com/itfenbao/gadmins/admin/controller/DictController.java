package com.itfenbao.gadmins.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.admin.data.dto.param.dict.AddDictParam;
import com.itfenbao.gadmins.admin.data.dto.param.dict.UpdateDictParam;
import com.itfenbao.gadmins.admin.data.dto.query.DictQuery;
import com.itfenbao.gadmins.admin.entity.Dict;
import com.itfenbao.gadmins.admin.service.IDictService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.web.JsonResult;
import com.itfenbao.gadmins.core.web.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "系统字典")
public class DictController {

    @Autowired
    IDictService dictService;

    @PostMapping()
    public JsonResult create(@RequestBody AddDictParam param) {
        Dict dict = new Dict();
        dict.setPId(param.getPId());
        dict.setTitle(param.getTitle());
        dict.setDCode(param.getDcode());
        dict.setIndexValue(param.getIndexValue());
        dict.setDValue(param.getDvalue());
        dictService.save(dict);
        return JsonResult.success();
    }

    @PutMapping("/{id}")
    public JsonResult update(@PathVariable Integer id, @RequestBody UpdateDictParam param) {
        Dict dict = new Dict();
        dict.setId(id);
        dict.setTitle(param.getTitle());
        dict.setDCode(param.getDcode());
        dict.setIndexValue(param.getIndexValue());
        dict.setDValue(param.getDvalue());
        dictService.updateById(dict);
        return JsonResult.success();
    }

    @DeleteMapping("/{ids}")
    public JsonResult deletes(@PathVariable List<Integer> ids) {
        dictService.removeByIds(ids);
        return JsonResult.success();
    }

    @GetMapping()
    @ApiOperation("分页查询")
    public JsonResult<PageData<Dict>> list(final DictQuery query) {
        Page<Dict> page = new Page<>(query.getCurrent(), query.getPageSize());
        dictService.page(page, Wrappers.<Dict>lambdaQuery().isNull(Dict::getPId));
        return JsonResult.success(PageData.get(page));
    }

    @GetMapping("/list/{pid}")
    @ApiOperation("查询字典数据")
    public JsonResult<PageData<Dict>> allValDict(@ApiParam(value = "字典父ID", required = true) @PathVariable Integer pid, final DictQuery query) {
        Page<Dict> page = new Page<>(query.getCurrent(), query.getPageSize());
        dictService.page(page, Wrappers.<Dict>lambdaQuery().eq(Dict::getPId, pid).orderByAsc(Dict::getIndexValue));
        return JsonResult.success(PageData.get(page));
    }

}
