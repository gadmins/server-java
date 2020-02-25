package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.dto.param.accout.UpdateAccoutParam;
import com.itfenbao.gadmins.admin.data.dto.query.AccoutQuery;
import com.itfenbao.gadmins.admin.data.vo.AccoutVO;
import com.itfenbao.gadmins.admin.entity.Accout;
import com.itfenbao.gadmins.admin.entity.RlUserRole;
import com.itfenbao.gadmins.admin.mapper.AccoutMapper;
import com.itfenbao.gadmins.admin.service.IAccoutService;
import com.itfenbao.gadmins.admin.service.IRlUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    IRlUserRoleService userRoleService;

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
        wrapper.eq("_accout.enable", 1);
        if (!StringUtils.isEmpty(query.getName())) {
            wrapper.like("_accout.name", query.getName());
        }
        if (query.getRoleId() > -1) {
            wrapper.eq("_role.id", query.getRoleId());
        }
        // 只查询非管理员账号
        wrapper.eq("_role.super_admin", 0);
        wrapper.groupBy("_accout.id");
        return this.baseMapper.getListByPage(page, wrapper);
    }

    @Override
    public void updateAccout(Integer id, UpdateAccoutParam param) {
        // 更新accout
        Accout accout = new Accout();
        accout.setId(id);
        accout.setName(param.getName());
        this.updateById(accout);

        // 更新用户角色
        this.baseMapper.realDelete(Wrappers.<RlUserRole>lambdaQuery().eq(RlUserRole::getUserId, id));
        List<RlUserRole> roles = new ArrayList<>();
        param.getRoles().forEach(roleId -> {
            RlUserRole userRole = new RlUserRole();
            userRole.setUserId(id);
            userRole.setRoleId(roleId);
            roles.add(userRole);
        });
        userRoleService.saveBatch(roles);
    }
}
