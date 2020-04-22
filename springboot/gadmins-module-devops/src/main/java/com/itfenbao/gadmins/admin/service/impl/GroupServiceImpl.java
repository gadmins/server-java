package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.dto.query.GroupQuery;
import com.itfenbao.gadmins.admin.entity.DatawayGroup;
import com.itfenbao.gadmins.admin.mapper.GroupMapper;
import com.itfenbao.gadmins.admin.service.IGroupService;
import com.itfenbao.gadmins.core.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if (!StringUtils.isEmpty(query.getGroupType())) {
            wrapper.eq(DatawayGroup::getGroupType, query.getGroupType());
        }
        return this.page(page, wrapper);
    }

}
