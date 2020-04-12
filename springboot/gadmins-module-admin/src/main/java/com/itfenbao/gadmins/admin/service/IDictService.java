package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.dto.query.DictQuery;
import com.itfenbao.gadmins.admin.entity.Dict;

/**
 * <p>
 * 系统字典表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
public interface IDictService extends IService<Dict> {

    Page<Dict> getListByPage(DictQuery query);

}
