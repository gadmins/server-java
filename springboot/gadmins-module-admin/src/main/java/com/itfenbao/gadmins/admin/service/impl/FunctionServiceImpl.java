package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.admin.mapper.FunctionMapper;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统功能表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements IFunctionService {

    @Override
    public boolean saveOrUpdateByCode(String code, Function function) {
        return false;
    }

    @Override
    public List<FunctionVO> getListByNullBtnGroup() {
        return this.baseMapper.getListByNullBtnGroup();
    }

    @Override
    public List<Integer> getPIdsByRoles(List<Integer> roleIds) {
        QueryWrapper wrapper = Wrappers.query()
                .isNotNull("_function.p_id")
                .in("_function_role.role_id", roleIds)
                .groupBy("_function.p_id");
        return this.baseMapper.queryPIds(wrapper);
    }
}
