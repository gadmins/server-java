package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.itfenbao.gadmins.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统功能表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_function")
public class Function extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 功能编码
     */
    private String code;

    /**
     * 功能描述
     */
    private String desc;

    /**
     * 功能接口api
     */
    private String apiUrl;

    /**
     * 前端路由
     */
    private String frontUrl;

    /**
     * 父级ID
     */
    private Integer pId;

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
