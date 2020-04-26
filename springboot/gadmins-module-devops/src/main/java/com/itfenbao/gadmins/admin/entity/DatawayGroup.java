package com.itfenbao.gadmins.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * dataway分组表
 * </p>
 *
 * @author itfenbao
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dataway_group")
@ApiModel(value = "DatawayGroup对象", description = "dataway分组表")
public class DatawayGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分组")
    private String groupType;

    @ApiModelProperty(value = "分组URL前缀")
    private String urlPrefix;

    @ApiModelProperty(value = "组描述")
    @TableField("`desc`")
    private String desc;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Boolean deleted;

}
