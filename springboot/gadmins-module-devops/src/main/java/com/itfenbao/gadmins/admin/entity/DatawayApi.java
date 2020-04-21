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
 * dataway接口表
 * </p>
 *
 * @author itfenbao
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dataway_api")
@ApiModel(value = "DatawayApi对象", description = "dataway接口表")
public class DatawayApi extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分组ID")
    private Integer groupId;

    @ApiModelProperty(value = "功能ID")
    private Integer funcId;

    @ApiModelProperty(value = "状态：0 草稿 1 发布")
    private Integer status;

    @ApiModelProperty(value = "请求方法")
    private String apiMethod;

    @ApiModelProperty(value = "请求路径")
    private String apiPath;

    @ApiModelProperty(value = "注释")
    private String apiComment;

    @ApiModelProperty(value = "脚本内容")
    private String apiScript;

    @ApiModelProperty(value = "请求结构")
    private String apiReqSchema;

    @ApiModelProperty(value = "响应结构")
    private String apiRespSchema;

    @ApiModelProperty(value = "脚本类型")
    private String scriptType;


}
