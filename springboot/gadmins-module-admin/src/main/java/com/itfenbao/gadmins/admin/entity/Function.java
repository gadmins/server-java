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
 * 系统功能表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_function")
@ApiModel(value="Function对象", description="系统功能表")
public class Function extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能组")
    private String funGroup;

    @ApiModelProperty(value = "功能编码")
    private String funCode;

    @ApiModelProperty(value = "功能标题")
    private String title;

    @ApiModelProperty(value = "功能描述")
    private String funDesc;

    @ApiModelProperty(value = "前端路由")
    private String frontUrl;

    @ApiModelProperty(value = "父级ID")
    private Integer pId;


}
