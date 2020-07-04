package com.itfenbao.gadmins.core.web.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface DbMapper {

    @Select("select database()")
    String queryCurrenDBName();

    IPage<Map> listTableByPage(IPage<Map> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    IPage<Map> listColumnByPage(IPage<Map> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    IPage<Map> listTableDataByPage(IPage<Map> page, @Param("name") String name, @Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(*) from information_schema.TABLES where table_name = #{tableName}")
    @ResultType(Integer.class)
    int isTableExist(String tableName);

    @Update("rename table ${name} to ${newName}")
    void renameTableName(@Param("name") String name, @Param("newName") String newName);

    @Update("alter table ${name} ${condition}")
    void alterTable(@Param("name") String tableName, @Param("condition") String condition);

    @Select("SHOW CREATE TABLE ${name}")
    @ResultType(Map.class)
    Map<String, String> getCreateDDL(@Param("name") String name);

    void createTable(@Param("name") String name, @Param("comment") String comment, boolean hasDelete);

    @Update("drop table ${name}")
    void dropTable(@Param("name") String name);

    @Update("truncate table ${name}")
    void truncateTable(@Param("name") String name);
}
