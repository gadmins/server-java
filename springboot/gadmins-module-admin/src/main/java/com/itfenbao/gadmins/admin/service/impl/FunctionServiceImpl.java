package com.itfenbao.gadmins.admin.service.impl;

import cn.hutool.core.lang.func.Func;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.vo.AuthFunciontVO;
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
                if (function.getPId() == null) {
                    // 设置MenuFuncId
                    Function _u = new Function();
                    _u.setId(function.getId());
                    _u.setMenuFuncId(function.getId());
                    this.updateById(_u);
                }
                functionPoint.setFuncId(function.getId());
                return true;
            }
        } else {
            functionPoint.setFuncId(one.getId());
            Function _update = new Function();
            _update.setId(one.getId());
            _update.setFrontUrl(functionPoint.getUrl());
            _update.setBtnGroup(functionPoint.getBtnGroup());
            _update.setSortNumber(functionPoint.getSort());
            if (this.updateById(_update)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<FunctionVO> getListPidIsNull() {
        return this.baseMapper.getListPidIsNull();
    }

    @Override
    public List<Integer> getPIdsByRoles(List<Integer> roleIds) {
        QueryWrapper wrapper = Wrappers.query();
        //.isNotNull("_function.p_id");
        if (!CollectionUtils.isEmpty(roleIds)) {
            wrapper.in("_function_role.role_id", roleIds);
        }
        wrapper.groupBy("_function.menu_func_id");
        return this.baseMapper.queryPIds(wrapper);
    }

    @Override
    public List<AuthFunciontVO> getFunctionsByRoles(List<Integer> roleIds) {
        QueryWrapper wrapper = Wrappers.query();
        if (!CollectionUtils.isEmpty(roleIds)) {
            wrapper.in("_function_role.role_id", roleIds);
        }
        wrapper.groupBy("_function.id");
        return this.baseMapper.queryAuthFunctions(wrapper);
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
        if (!StringUtils.isEmpty(functionPoint.getParentCode())) {
            Function one = this.getOne(Wrappers.<Function>lambdaQuery().eq(Function::getFuncCode, functionPoint.getParentCode()));
            if (one != null) {
                function.setPId(one.getId());
                function.setMenuFuncId(one.getMenuFuncId());
                if (one.getVirtualMenu() == false) {
                    // 标记为VirtualMenu
                    Function functionParentCode = new Function();
                    functionParentCode.setId(one.getId());
                    functionParentCode.setVirtualMenu(true);
                    this.updateById(functionParentCode);
                    // FIXME: 复制: 仅用于角色分配查询
                    Function copyFunc = new Function();
                    copyFunc.setFuncGroup(one.getFuncGroup());
                    copyFunc.setFuncDesc(one.getFuncDesc());
                    copyFunc.setTitle("查询");
                    copyFunc.setFuncCode(one.getFuncCode() + "-vm");
                    copyFunc.setBtnGroup(one.getBtnGroup());
                    copyFunc.setBtnIcon(one.getBtnIcon());
                    copyFunc.setPId(one.getId());
                    copyFunc.setMenuFuncId(one.getMenuFuncId());
                    this.save(copyFunc);
                }
            }
        } else if (functionPoint.getParentFuncId() != null) {
            function.setPId(functionPoint.getParentFuncId());
            function.setMenuFuncId(functionPoint.getParentFuncId());
        }
        if (function.getPId() == null) {
            function.setBtnGroup(null);
        }
        return function;
    }
}
