package com.itfenbao.gadmins.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.app.data.dto.query.AccoutQuery;
import com.itfenbao.gadmins.app.data.vo.AccoutVO;
import com.itfenbao.gadmins.app.entity.Accout;
import com.itfenbao.gadmins.app.entity.User;
import com.itfenbao.gadmins.app.mapper.AccoutMapper;
import com.itfenbao.gadmins.app.service.IAccoutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-16
 */
@Service
public class AccoutServiceImpl extends ServiceImpl<AccoutMapper, Accout> implements IAccoutService {

    @Override
    public Accout findByNameAndPassword(String userName, String password) {
        LambdaQueryWrapper<Accout> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Accout::getName, userName).eq(Accout::getPassword, password);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<AccoutVO> getListByPage(AccoutQuery query) {
        Page<AccoutVO> page = new Page<>(query.getCurrent(), query.getPageSize());
        QueryWrapper<Accout> wrapper = Wrappers.query();
        if (!StringUtils.isEmpty(query.getName())) {
            wrapper.like("_accout.name", query.getName());
        }
        if (query.getRoleId() > -1) {
            wrapper.eq("_role.id", query.getRoleId());
        }
        return this.baseMapper.getListByPage(page, wrapper);
    }
}
