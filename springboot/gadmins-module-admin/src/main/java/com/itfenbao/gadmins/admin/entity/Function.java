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
 * @since 2020-03-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_function")
@ApiModel(value="Function对象", description="系统功能表")
public class Function extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能组")
    private String funcGroup;

    @ApiModelProperty(value = "功能编码")
    private String funcCode;

    @ApiModelProperty(value = "功能描述")
    private String funcDesc;

    @ApiModelProperty(value = "功能标题")
    private String title;

    @ApiModelProperty(value = "按钮组")
    private String btnGroup;

    @ApiModelProperty(value = "按钮图标")
    private String btnIcon;

    @ApiModelProperty(value = "是否是外链")
    private Boolean elink;

    @ApiModelProperty(value = "前端路由")
    private String frontUrl;

    @ApiModelProperty(value = "父级ID")
    private Integer pId;

    @ApiModelProperty(value = "虚拟菜单")
    private Boolean virtualMenu;

    @ApiModelProperty(value = "排序")
    private Integer sortNumber;


}
