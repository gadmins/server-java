package com.itfenbao.gadmins.devops.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * dataway功能表
 * </p>
 *
 * @author itfenbao
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dataway_function")
@ApiModel(value="Function对象", description="dataway功能表")
public class DatawayFunction extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "api id")
    private Integer apiId;

    @ApiModelProperty(value = "是否关联菜单")
    private Boolean menu;

    @ApiModelProperty(value = "功能编码")
    private String fCode;

    @ApiModelProperty(value = "功能名称")
    private String fName;

    @ApiModelProperty(value = "父级功能编码")
    private String pFCode;

    @ApiModelProperty(value = "排序")
    private Integer sortNumber;


}
