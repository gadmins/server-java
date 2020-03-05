package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.data.vo.FunctionPointVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.admin.entity.FunctionConfig;
import com.itfenbao.gadmins.admin.mapper.FunctionConfigMapper;
import com.itfenbao.gadmins.admin.service.IFunctionConfigService;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.itfenbao.gadmins.core.web.query.PageQuery;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPointConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统功能配置表-前端展示 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class FunctionConfigServiceImpl extends ServiceImpl<FunctionConfigMapper, FunctionConfig> implements IFunctionConfigService {

    @Autowired
    IFunctionService functionService;

    @Override
    public boolean saveOrUpdate(FunctionPointConfig pointConfig) {
        FunctionConfig one = this.getOne(Wrappers.<FunctionConfig>lambdaQuery().eq(FunctionConfig::getFuncId, pointConfig.getFuncId()));
        if (one == null) {
            FunctionConfig functionConfig = new FunctionConfig();
            functionConfig.setFuncId(pointConfig.getFuncId());
            functionConfig.setApiUrl(pointConfig.getUrl());
            functionConfig.setApiMethod(pointConfig.getMethod());
            functionConfig.setCommonSchema(pointConfig.getSchema());
            if (this.save(functionConfig)) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    @Override
    public Page<FunctionPointVO> getListByPage(PageQuery query, Integer funcPid) {
        Page<FunctionPointVO> page = new Page<>(query.getCurrent(), query.getPageSize());
        List<Integer> ids = getFuncIdsByPid(funcPid);
        Wrapper wrapper = Wrappers.query().in("_function.id", ids).orderByAsc("_function.sort_number");
        this.baseMapper.getListByPage(page, wrapper);
        if (query.getCurrent() == 1 && page.getRecords().size() > 0) {
            Page<FunctionPointVO> selfPage = new Page<>(0, 1);
            Wrapper selfWrapper = Wrappers.query().eq("_function.id", funcPid);
            this.baseMapper.getListByPage(selfPage, selfWrapper);
            page.getRecords().add(0, selfPage.getRecords().get(0));
        }
        return page;
    }

    private List<Integer> getFuncIdsByPid(Integer pid) {
        List<Integer> funcIds = new ArrayList<>();
        // FIXME：查询子集 且忽略func_code以-vm结尾的数据
        List<Integer> ids = this.functionService.list(Wrappers.<Function>lambdaQuery()
                .eq(Function::getPId, pid))
                .stream()
                .filter(func -> !func.getFuncCode().endsWith("-vm"))
                .map(func -> func.getId()).collect(Collectors.toList());
        if (ids.size() > 0) {
            ids.forEach(i -> {
                funcIds.addAll(getFuncIdsByPid(i));
            });
            funcIds.addAll(ids);
        }
        return funcIds;
    }
}
