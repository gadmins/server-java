package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.admin.data.dto.query.DictQuery;
import com.itfenbao.gadmins.admin.entity.Dict;
import com.itfenbao.gadmins.admin.mapper.DictMapper;
import com.itfenbao.gadmins.admin.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 系统字典表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Override
    public Page<Dict> getListByPage(DictQuery query) {
        Page<Dict> page = new Page<>(query.getCurrent(), query.getPageSize());
        LambdaQueryWrapper<Dict> wrapper =  Wrappers.<Dict>lambdaQuery().isNull(Dict::getPId);
        if(!StringUtils.isEmpty(query.getDcode())) {
            wrapper.like(Dict::getDCode, query.getDcode());
        }
        if(!StringUtils.isEmpty(query.getTitle())) {
            wrapper.like(Dict::getTitle, query.getTitle());
        }
        String[] createdAt = query.getCreatedAt();
        if (createdAt != null && createdAt.length > 1) {
            wrapper.between(Dict::getCreatedAt, createdAt[0], createdAt[1]);
        }
        return this.page(page, wrapper);
    }
}
