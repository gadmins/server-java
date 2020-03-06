package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.vo.AuthFunciontVO;
import com.itfenbao.gadmins.admin.data.vo.FunctionVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPoint;

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

    boolean saveOrUpdate(FunctionPoint functionPoint);

    List<FunctionVO> getListPidIsNull();

    List<Integer> getPIdsByRoles(List<Integer> roleIds);

    List<AuthFunciontVO> getFunctionsByRoles(List<Integer> roleIds);

}
