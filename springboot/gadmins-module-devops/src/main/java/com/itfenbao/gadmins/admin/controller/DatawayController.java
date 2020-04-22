package com.itfenbao.gadmins.admin.controller;

import cn.hutool.core.map.MapUtil;
import com.itfenbao.gadmins.admin.data.dto.query.ApiQuery;
import com.itfenbao.gadmins.admin.data.dto.query.GroupQuery;
import com.itfenbao.gadmins.admin.entity.DatawayApi;
import com.itfenbao.gadmins.admin.entity.DatawayGroup;
import com.itfenbao.gadmins.admin.service.IApiService;
import com.itfenbao.gadmins.admin.service.IDataQLService;
import com.itfenbao.gadmins.admin.service.IGroupService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.annotation.Function;
import com.itfenbao.gadmins.core.annotation.Menu;
import com.itfenbao.gadmins.core.web.result.JsonPageResult;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import com.itfenbao.gadmins.core.web.result.JsonReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(AppConfig.AdminRoute.ADMIN_DATAWAY)
@Api(tags = "系统动态接口", hidden = AppConfig.HIDDEN_SYS_API)
@Menu(value = "dataway", parentCode = AppConfig.SysNavMenu.DEVOPS, sort = 1, icon = "cloud", title = "动态接口管理", desc = "动态接口管理", url = "/system/dataway")
public class DatawayController {

    @Autowired
    IDataQLService dataQLService;

    @Autowired
    IGroupService groupService;

    @Autowired
    IApiService apiService;

    @GetMapping("/group/option")
    @ApiOperation(value = "动态接口支持的分组")
    public JsonResult group() {
        return JsonResult.success(AppConfig.GROUPS);
    }

    @Function(value = "sys:dataway:group:list", sort = 0, title = "查询接口分组", desc = "查询动态接口分组", menu = true)
    @GetMapping("/group")
    @ApiOperation(value = "查询接口分组")
    public JsonPageResult<DatawayGroup> list(GroupQuery query) {
        return JsonPageResult.success(groupService.listByPage(query));
    }

    @Function(value = "sys:dataway:group:add", sort = 1, title = "添加接口分组", desc = "添加动态接口分组")
    @PostMapping("/group")
    @ApiOperation(value = "添加接口分组")
    public JsonResult addGroup(@RequestBody DatawayGroup datawayGroup) {
        boolean ret = groupService.save(datawayGroup);
        return ret ? JsonResult.success() : JsonResult.failMessage("创建失败");
    }

    @Function(value = "sys:dataway:group:getbyid", sort = 2, title = "查询分组详情", desc = "查询分组详情")
    @GetMapping("/group/{id}")
    @ApiOperation(value = "查询分组详情")
    public JsonResult addGroup(@PathVariable Integer id) {
        return JsonResult.success(groupService.getById(id));
    }

    @Function(value = "sys:dataway:group:del", sort = 3, title = "删除接口分组", desc = "删除动态接口分组")
    @DeleteMapping("/group/{id}")
    @ApiOperation(value = "删除接口分组")
    public JsonResult delGroup(@PathVariable List<Integer> id) {
        boolean ret = groupService.removeByIds(id);
        return ret ? JsonResult.success() : JsonResult.failMessage("删除失败");
    }

    @Function(value = "sys:dataway:group:update", sort = 4, title = "更新接口分组", desc = "更新动态接口分组")
    @PutMapping("/group/{id}")
    @ApiOperation(value = "更新接口分组")
    public JsonResult updateGroup(@PathVariable Integer id, @RequestBody DatawayGroup datawayGroup) {
        datawayGroup.setId(id);
        DatawayGroup idGroup = groupService.getById(id);
        boolean ret = groupService.updateById(datawayGroup);
        if (!StringUtils.isEmpty(datawayGroup.getUrlPrefix()) && !idGroup.getUrlPrefix().equalsIgnoreCase(datawayGroup.getUrlPrefix())) {
            apiService.updateUrlBy(id, idGroup.getUrlPrefix(), datawayGroup.getUrlPrefix());
        }
        return ret ? JsonResult.success() : JsonResult.failMessage("更新失败");
    }


    @Function(value = "sys:dataway:api:list", sort = 5, title = "查询接口", desc = "查询动态接口")
    @GetMapping("/api")
    @ApiOperation(value = "查询接口")
    public JsonPageResult<DatawayApi> apilist(ApiQuery query) {
        return JsonPageResult.success(apiService.listByPage(query));
    }

    @Function(value = "sys:dataway:api:add", sort = 6, title = "添加接口", desc = "添加动态接口")
    @PostMapping("/api")
    @ApiOperation(value = "添加接口")
    public JsonResult addApi(@RequestBody DatawayApi datawayApi) {
        boolean ret = apiService.save(datawayApi);
        return ret ? JsonResult.success() : JsonResult.failMessage("创建失败");
    }

    @Function(value = "sys:dataway:api:getbyid", sort = 7, title = "查询接口详情", desc = "查询接口详情")
    @GetMapping("/api/{id}")
    @ApiOperation(value = "查询接口详情")
    public JsonResult detailApi(@PathVariable Integer id) {
        return JsonResult.success(apiService.getById(id));
    }

    @Function(value = "sys:dataway:api:del", sort = 8, title = "删除接口", desc = "删除动态接口")
    @DeleteMapping("/api/{id}")
    @ApiOperation(value = "删除接口")
    public JsonResult delApi(@PathVariable List<Integer> id) {
        boolean ret = apiService.removeByIds(id);
        return ret ? JsonResult.success() : JsonResult.failMessage("删除失败");
    }

    @Function(value = "sys:dataway:api:update", sort = 9, title = "更新接口", desc = "更新动态接口")
    @PutMapping("/api/{id}")
    @ApiOperation(value = "更新接口")
    public JsonResult updateApi(@PathVariable Integer id, @RequestBody DatawayApi datawayApi) {
        datawayApi.setId(id);
        DatawayApi idApi =  apiService.getById(id);
        if (idApi.getStatus() == 1) {
            return JsonResult.failMessage("接口已发布，禁止更新");
        }
        boolean ret = apiService.updateById(datawayApi);
        return ret ? JsonResult.success() : JsonResult.failMessage("更新失败");
    }

    @Function(value = "sys:dataway:api:publish", sort = 10, title = "发布接口", desc = "发布接口")
    @PutMapping("/api/{id}/publish")
    @ApiOperation(value = "发布接口")
    public JsonResult publishApi(@PathVariable Integer id, Map<String, Object> params) {
        DatawayApi api = new DatawayApi();
        api.setId(id);
        api.setStatus(1);
        boolean ret = apiService.updateById(api);
        return ret ? JsonResult.success() : JsonResult.failMessage("发布失败");
    }

    @Function(value = "sys:dataway:api:offline", sort = 11, title = "下线接口", desc = "下线接口")
    @PutMapping("/api/{id}/offline")
    @ApiOperation(value = "下线接口")
    public JsonResult offlineApi(@PathVariable Integer id, Map<String, Object> params) {
        DatawayApi api = new DatawayApi();
        api.setId(id);
        api.setStatus(0);
        boolean ret = apiService.updateById(api);
        return ret ? JsonResult.success() : JsonResult.failMessage("发布失败");
    }

    @Function(value = "sys:dataway:test", sort = 12, title = "测试DataQL", desc = "测试DataQL")
    @GetMapping("/test")
    @ApiOperation(value = "测试DataQL")
    public JsonResult test(String type, String script, Map<String, Object> request) {
        Map<String, Object> ret = dataQLService.execuScript(type, script, request);
        int code = MapUtil.getInt(ret, "code");
        return code == JsonReturnCode.SUCCESS.getCode() ? JsonResult.success(ret) : JsonResult.fail(ret);
    }

}