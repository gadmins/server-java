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
 * 系统字典表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_dict")
@ApiModel(value="Dict对象", description="系统字典表")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码")
    private String dCode;

    @ApiModelProperty(value = "字典标题")
    private String title;

    @ApiModelProperty(value = "索引值")
    private Integer indexValue;

    @ApiModelProperty(value = "值")
    private String dValue;

    @ApiModelProperty(value = "父级ID")
    private Integer pId;


}
