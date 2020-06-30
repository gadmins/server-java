package com.itfenbao.gadmins.devops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itfenbao.gadmins.devops.entity.DatawayApi;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * dataway接口表 Mapper 接口
 * </p>
 *
 * @author itfenbao
 * @since 2020-04-21
 */
public interface ApiMapper extends BaseMapper<DatawayApi> {

    @Update("UPDATE sys_dataway_api SET api_path=REPLACE(api_path,#{oldPrefix},#{newPrefix})  WHERE group_id = #{groupId}")
    boolean updateUrlBy(Integer groupId, String oldPrefix, String newPrefix);

}
