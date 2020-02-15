package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_function")
public class Function extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 功能组
     */
    private String funGroup;

    /**
     * 功能编码
     */
    private String funCode;

    /**
     * 功能标题
     */
    private String title;

    /**
     * 功能描述
     */
    private String funDesc;

    /**
     * 前端路由
     */
    private String frontUrl;

    /**
     * 父级ID
     */
    private Integer pId;


}
