package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统功能配置表-前端展示
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_function_config")
public class FunctionConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
    private Integer funId;

    /**
     * 功能接口api
     */
    private String apiUrl;

    /**
     * 请求方法
     */
    private String apiMethod;

    /**
     * 通用schema
     */
    private String commonSchema;

    /**
     * 查询schema
     */
    private String searchSchema;


}
