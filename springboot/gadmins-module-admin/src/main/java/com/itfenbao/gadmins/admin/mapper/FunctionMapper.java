package com.itfenbao.gadmins.admin.mapper;

import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统功能表 Mapper 接口
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-15
 */
public interface FunctionMapper extends BaseMapper<Function> {

    List<FunctionVO> getListByNullBtnGroup();
}
