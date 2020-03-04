package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.entity.FunctionConfig;
import com.itfenbao.gadmins.admin.mapper.FunctionConfigMapper;
import com.itfenbao.gadmins.admin.service.IFunctionConfigService;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPointConfig;
import org.springframework.stereotype.Service;

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
}
