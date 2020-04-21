package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.dto.query.ApiQuery;
import com.itfenbao.gadmins.admin.entity.DatawayApi;
import com.itfenbao.gadmins.admin.mapper.ApiMapper;
import com.itfenbao.gadmins.admin.service.IApiService;
import com.itfenbao.gadmins.core.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if (!StringUtils.isEmpty(query.getGroupId())) {
            wrapper.eq(DatawayApi::getGroupId, query.getGroupId());
        }
        return this.page(page, wrapper);
    }
}
