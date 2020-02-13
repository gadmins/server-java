package com.itfenbao.gadmins.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据表基础Entity
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
public class BaseEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;

    /**
     * 创建人
     */
    protected Integer createdBy;

    /**
     * 更新人
     */
    protected Integer updatedBy;

    /**
     * 创建时间
     */
    protected LocalDateTime createdAt;

    /**
     * 更新时间
     */
    protected LocalDateTime updatedAt;

    /**
     * 逻辑删除
     */
    @TableLogic
    protected Boolean enable;
}
