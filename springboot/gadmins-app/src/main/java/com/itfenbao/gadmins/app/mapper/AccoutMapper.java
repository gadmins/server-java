package com.itfenbao.gadmins.app.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.app.data.vo.AccoutVO;
import com.itfenbao.gadmins.app.entity.Accout;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-16
 */
public interface AccoutMapper extends BaseMapper<Accout> {
    Page<AccoutVO> getListByPage(Page<AccoutVO> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
