package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.dto.query.ApiQuery;
import com.itfenbao.gadmins.admin.entity.DatawayApi;

/**
 * <p>
 * dataway接口表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-04-21
 */
public interface IApiService extends IService<DatawayApi> {

    IPage<DatawayApi> listByPage(ApiQuery query);

    boolean updateUrlBy(Integer groupId, String oldPrefix, String newPrefix);

}
