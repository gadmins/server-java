package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.admin.mapper.FunctionMapper;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPoint;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public boolean saveOrUpdate(FunctionPoint functionPoint) {
        Function one = this.getOne(Wrappers.<Function>lambdaQuery().eq(Function::getFuncCode, functionPoint.getCode()));
        if (one == null) {
            Function function = createFunction(functionPoint);
            if (this.save(function)) {
                functionPoint.setFuncId(function.getId());
                return true;
            }
        } else {
            functionPoint.setFuncId(one.getId());
            return true;
        }
        return false;
    }

    @Override
    public List<FunctionVO> getListPidIsNull() {
        return this.baseMapper.getListPidIsNull();
    }

    @Override
    public List<Integer> getPIdsByRoles(List<Integer> roleIds) {
        QueryWrapper wrapper = Wrappers.query().isNotNull("_function.p_id");
        if (!CollectionUtils.isEmpty(roleIds)) {
            wrapper.in("_function_role.role_id", roleIds);
        }
        wrapper.groupBy("_function.p_id");
        return this.baseMapper.queryPIds(wrapper);
    }

    private Function createFunction(FunctionPoint functionPoint) {
        Function function = new Function();
        function.setFuncGroup(functionPoint.getGroup());
        function.setFuncCode(functionPoint.getCode());
        function.setTitle(functionPoint.getTitle());
        function.setFuncDesc(functionPoint.getDesc());
        function.setFrontUrl(functionPoint.getUrl());
        function.setBtnGroup(functionPoint.getBtnGroup());
        function.setSortNumber(functionPoint.getSort());
        if (functionPoint.getParentFuncId() != null) {
            function.setPId(functionPoint.getParentFuncId());
        } else if (!StringUtils.isEmpty(functionPoint.getParentCode())) {
            Function one = this.getOne(Wrappers.<Function>lambdaQuery().eq(Function::getFuncCode, functionPoint.getParentCode()));
            if (one != null)
                function.setPId(one.getId());
        }
        return function;
    }
}
