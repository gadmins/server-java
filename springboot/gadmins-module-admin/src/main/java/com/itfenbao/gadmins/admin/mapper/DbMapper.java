package com.itfenbao.gadmins.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface DbMapper {

    @Select("select database()")
    String queryCurrenDBName();

    IPage<Map> listTableByPage(IPage<Map> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    IPage<Map> listColumnByPage(IPage<Map> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    IPage<Map> listTableDataByPage(IPage<Map> page, @Param("name") String name, @Param(Constants.WRAPPER) Wrapper wrapper);

}
