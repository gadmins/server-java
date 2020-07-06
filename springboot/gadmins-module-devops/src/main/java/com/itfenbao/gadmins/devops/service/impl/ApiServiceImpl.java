package com.itfenbao.gadmins.devops.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.core.utils.PageUtils;
import com.itfenbao.gadmins.devops.data.dto.query.ApiQuery;
import com.itfenbao.gadmins.devops.entity.DatawayApi;
import com.itfenbao.gadmins.devops.mapper.ApiMapper;
import com.itfenbao.gadmins.devops.service.IApiService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * dataway接口表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-04-21
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, DatawayApi> implements IApiService {

    @Override
    public IPage<DatawayApi> listByPage(ApiQuery query) {
        IPage<DatawayApi> page = PageUtils.page(query);
        LambdaQueryWrapper<DatawayApi> wrapper = Wrappers.lambdaQuery();
        if (query.getGroupId() != null) {
            wrapper.eq(DatawayApi::getGroupId, query.getGroupId());
        }
        if (query.getStatus() != null) {
            wrapper.eq(DatawayApi::getStatus, query.getStatus());
        }
        if (StringUtils.isNotBlank(query.getApiMethod())) {
            wrapper.eq(DatawayApi::getApiMethod, query.getApiMethod());
        }
        if (StringUtils.isNotBlank(query.getApiPath())) {
            wrapper.like(DatawayApi::getApiPath, query.getApiPath());
        }
        if (StringUtils.isNotBlank(query.getApiComment())) {
            wrapper.like(DatawayApi::getApiComment, query.getApiComment());
        }
        return this.page(page, wrapper);
    }

    @Override
    public boolean updateUrlBy(Integer groupId, String oldPrefix, String newPrefix) {
        return this.baseMapper.updateUrlBy(groupId, oldPrefix, newPrefix);
    }
}
