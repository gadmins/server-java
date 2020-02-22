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
 * 系统菜单表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_menu")
@ApiModel(value="Menu对象", description="系统菜单表")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码")
    private String mCode;

    @ApiModelProperty(value = "菜单文本")
    private String txt;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单类型")
    private String type;

    @ApiModelProperty(value = "排序")
    private Integer sortNumber;

    @ApiModelProperty(value = "功能ID")
    private Integer funcId;

    @ApiModelProperty(value = "父级ID")
    private Integer pId;


}
