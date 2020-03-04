package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.entity.FunctionConfig;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPointConfig;

/**
 * <p>
 * 系统功能配置表-前端展示 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
public interface IFunctionConfigService extends IService<FunctionConfig> {
    boolean saveOrUpdate(FunctionPointConfig pointConfig);
}
