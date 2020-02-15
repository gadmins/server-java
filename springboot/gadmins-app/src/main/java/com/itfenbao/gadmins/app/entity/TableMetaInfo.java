package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统元信息扩展表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_table_meta_info")
public class TableMetaInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String filedName;

    /**
     * 值类型
     */
    private String valueType;


}
