package com.itfenbao.gadmins.admin.data.dto.query;

import com.itfenbao.gadmins.core.web.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author itfenbao
 * @version 1.0
 * @date 2020/4/12 7:16 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleQuery extends PageQuery {
    private String name;
    private String[] createdAt;
}
