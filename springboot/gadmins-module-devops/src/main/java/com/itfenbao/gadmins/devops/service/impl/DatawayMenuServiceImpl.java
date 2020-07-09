package com.itfenbao.gadmins.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.core.web.service.IMenuScanService;
import com.itfenbao.gadmins.devops.entity.DatawayMenu;
import com.itfenbao.gadmins.devops.mapper.DatawayMenuMapper;
import com.itfenbao.gadmins.devops.service.IDatawayFunctionService;
import com.itfenbao.gadmins.devops.service.IDatawayMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * dataway菜单表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-07-09
 */
@Service
public class DatawayMenuServiceImpl extends ServiceImpl<DatawayMenuMapper, DatawayMenu> implements IDatawayMenuService, IMenuScanService {

    @Resource
    IDatawayFunctionService datawayFunctionService;

    @Override
    public void scanMenu() {

    }

}
