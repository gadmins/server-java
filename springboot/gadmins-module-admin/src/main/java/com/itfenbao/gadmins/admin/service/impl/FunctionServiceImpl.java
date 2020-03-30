package com.itfenbao.gadmins.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.vo.AuthFunciontVO;
import com.itfenbao.gadmins.admin.data.vo.AuthFunctionPointVO;
import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.admin.mapper.FunctionMapper;
import com.itfenbao.gadmins.admin.service.IAccountService;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.admin.service.IRlAccountRoleService;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    IAccountService accountService;

    @Autowired
    IRlAccountRoleService accountRoleService;

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

    @Override
    public boolean hasFunctionById(Integer id) {
        int accountId = Integer.parseInt(TokenUtils.getUniqueIdFromToken());
        boolean isSuperAdmin = accountService.isSuperAdmin(accountId);
        if (isSuperAdmin) {
            return true;
        }
        List<Integer> roleIds = accountRoleService.getRoleIdsByAccountId(accountId);
        return this.baseMapper.queryIdsByRoles(
                Wrappers.query()
                        .eq("_function.id", id)
                        .in("_function_role.role_id", roleIds)
                        .groupBy("_function.id")).size() > 0;
    }

    @Override
    public List<AuthFunctionPointVO> getListForCurrentAccountById(Integer id) {
        List<AuthFunctionPointVO> funcs = null;
        // 根据当前用户查询授权的功能点
        int accountId = Integer.parseInt(TokenUtils.getUniqueIdFromToken());
        boolean isSuperAdmin = accountService.isSuperAdmin(accountId);
        if (isSuperAdmin) {
            funcs = this.baseMapper.queryAllFunctionPoints(
                    Wrappers.query().eq("_function.p_id", id).groupBy("_function.id"))
                    .stream()
                    .filter(func -> !func.getCode().endsWith("-vm")).collect(Collectors.toList());
        } else {
            List<Integer> roleIds = accountRoleService.getRoleIdsByAccountId(accountId);
            funcs = this.baseMapper.quertByRoles(
                    Wrappers.query()
                            .eq("_function.p_id", id)
                            .in("_function_role.role_id", roleIds)
                            .groupBy("_function.id"));
        }
        return funcs;
    }

    @Override
    public List<AuthFunciontVO> getAuthFunciontVOS(List<Integer> roleIds) {
        List<AuthFunciontVO> funciontVOS;// 查询角色功能
        funciontVOS = this.getFunctionsByRoles(roleIds);

        List<AuthFunciontVO> realAuths = null;
        // 获取-vm的实际功能（即-vm的pid）
        List<Integer> vmIds = funciontVOS.stream().filter(i -> i.getCode().endsWith("-vm")).map(i -> i.getId()).collect(Collectors.toList());
        LambdaQueryWrapper<Function> funcWrapper = Wrappers.<Function>lambdaQuery();
        if (!CollectionUtils.isEmpty(vmIds)) {
            funcWrapper.in(Function::getId, vmIds);
            List<Integer> realIds = this.list(funcWrapper)
                    .stream().map(i -> i.getPId()).collect(Collectors.toList());
            realAuths = this.list(Wrappers.<Function>lambdaQuery().in(Function::getId, realIds))
                    .stream().map(i -> {
                        AuthFunciontVO funciontVO = new AuthFunciontVO();
                        funciontVO.setId(i.getId());
                        funciontVO.setUrl(i.getFrontUrl());
                        funciontVO.setCode(i.getFuncCode());
                        return funciontVO;
                    }).collect(Collectors.toList());
        }
        // 去重添加
        if (!CollectionUtils.isEmpty(realAuths)) {
            funciontVOS = CollUtil.addAllIfNotContains(funciontVOS, realAuths);
        }
        return funciontVOS;
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
