package com.itfenbao.gadmins.devops.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.core.utils.PageUtils;
import com.itfenbao.gadmins.devops.data.dto.query.GroupQuery;
import com.itfenbao.gadmins.devops.entity.DatawayGroup;
import com.itfenbao.gadmins.devops.mapper.GroupMapper;
import com.itfenbao.gadmins.devops.service.IGroupService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * dataway分组表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-04-21
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, DatawayGroup> implements IGroupService {

    @Override
    public IPage<DatawayGroup> listByPage(GroupQuery query) {
        IPage<DatawayGroup> page = PageUtils.page(query);
        LambdaQueryWrapper<DatawayGroup> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(query.getGroupType())) {
            wrapper.eq(DatawayGroup::getGroupType, query.getGroupType());
        }
        String[] createdAt = query.getCreatedAt();
        if (createdAt != null && createdAt.length > 1) {
            wrapper.between(DatawayGroup::getCreatedAt, createdAt[0], createdAt[1]);
        }
        return this.page(page, wrapper);
    }

}
