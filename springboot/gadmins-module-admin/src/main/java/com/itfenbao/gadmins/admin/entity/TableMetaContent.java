package com.itfenbao.gadmins.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统元信息扩展数据表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_table_meta_content")
@ApiModel(value="TableMetaContent对象", description="系统元信息扩展数据表")
public class TableMetaContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据ID")
    private Integer dataId;

    @ApiModelProperty(value = "元信息ID")
    private Integer tableMetaId;

    @ApiModelProperty(value = "值")
    private String feildValue;


}
