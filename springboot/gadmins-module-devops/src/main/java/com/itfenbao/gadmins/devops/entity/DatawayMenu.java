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
 * dataway菜单表
 * </p>
 *
 * @author itfenbao
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dataway_menu")
@ApiModel(value="Menu对象", description="dataway菜单表")
public class DatawayMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "group id")
    private Integer groupId;

    @ApiModelProperty(value = "菜单父级编码")
    private String pCode;

    @ApiModelProperty(value = "菜单编码")
    private String mCode;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "菜单链接")
    private String link;

    @ApiModelProperty(value = "排序")
    private Integer sortNumber;


}
