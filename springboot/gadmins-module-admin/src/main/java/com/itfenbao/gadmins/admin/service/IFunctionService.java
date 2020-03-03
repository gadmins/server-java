package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.Function;
import org.apache.ibatis.annotations.Param;

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

    boolean saveOrUpdateByCode(String code, Function function);

    List<FunctionVO> getListByNullBtnGroup();

    List<Integer> getPIdsByRoles(List<Integer> roleIds);

}
