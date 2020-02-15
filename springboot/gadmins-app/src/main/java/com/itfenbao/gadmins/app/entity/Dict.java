package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统字典表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String dCode;

    /**
     * 字典标题
     */
    private String title;

    /**
     * 索引值
     */
    private Integer indexValue;

    /**
     * 值
     */
    private String dValue;

    /**
     * 父级ID
     */
    private Integer pId;


}
