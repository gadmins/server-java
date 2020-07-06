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
 * 系统日志表
 * </p>
 *
 * @author itfenbao
 * @since 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_log")
@ApiModel(value = "Log对象", description = "系统日志表")
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "日志内容")
    private String log;

    @ApiModelProperty(value = "请求地址")
    private String uri;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "标记")
    private String tag;


}
