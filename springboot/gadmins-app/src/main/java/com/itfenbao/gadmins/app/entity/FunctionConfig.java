package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.itfenbao.gadmins.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统功能配置表-前端展示
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
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
     * 查询form
     */
    private String search;

    /**
     * 表结构
     */
    private String table;

    /**
     * 新增/修改form
     */
    private String form;

    /**
     * 创建人
     */
    private Integer createdBy;

    /**
     * 更新人
     */
    private Integer updatedBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 是否启用
     */
    private Boolean enable;


}
