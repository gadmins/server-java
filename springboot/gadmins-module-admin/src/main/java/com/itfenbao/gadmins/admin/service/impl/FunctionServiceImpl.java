package com.itfenbao.gadmins.admin.service.impl;

import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.admin.mapper.FunctionMapper;
import com.itfenbao.gadmins.admin.service.IFunctionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public List<FunctionVO> getListByNullBtnGroup() {
        return this.baseMapper.getListByNullBtnGroup();
    }
}
