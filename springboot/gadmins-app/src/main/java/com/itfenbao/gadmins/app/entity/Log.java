package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_log")
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 日志内容
     */
    private String log;

    /**
     * 标记
     */
    private String tag;


}
