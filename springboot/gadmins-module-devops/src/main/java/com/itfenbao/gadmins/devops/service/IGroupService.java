package com.itfenbao.gadmins.devops.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.devops.data.dto.query.GroupQuery;
import com.itfenbao.gadmins.devops.entity.DatawayGroup;

/**
 * <p>
 * dataway分组表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-04-21
 */
public interface IGroupService extends IService<DatawayGroup> {

    IPage<DatawayGroup> listByPage(GroupQuery query);

}
