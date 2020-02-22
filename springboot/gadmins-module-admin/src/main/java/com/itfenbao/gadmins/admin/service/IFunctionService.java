package com.itfenbao.gadmins.admin.service;

import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统功能表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
public interface IFunctionService extends IService<Function> {

    List<FunctionVO> getListByNullBtnGroup();

}
