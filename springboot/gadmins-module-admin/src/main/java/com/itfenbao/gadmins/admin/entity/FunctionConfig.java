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
 * 系统功能配置表-前端展示
 * </p>
 *
 * @author itfenbao
 * @since 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_function_config")
@ApiModel(value="FunctionConfig对象", description="系统功能配置表-前端展示")
public class FunctionConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能ID")
    private Integer funcId;

    @ApiModelProperty(value = "功能接口api")
    private String apiUrl;

    @ApiModelProperty(value = "请求方法")
    private String apiMethod;

    @ApiModelProperty(value = "通用schema")
    private String dataSchema;

}
