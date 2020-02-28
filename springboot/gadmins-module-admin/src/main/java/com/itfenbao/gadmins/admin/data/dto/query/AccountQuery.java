package com.itfenbao.gadmins.admin.data.dto.query;

import com.itfenbao.gadmins.core.web.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountQuery extends PageQuery {

    private String name;
    private long roleId = -1;

}
