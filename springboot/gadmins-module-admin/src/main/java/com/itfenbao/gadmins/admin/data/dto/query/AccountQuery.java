package com.itfenbao.gadmins.admin.data.dto.query;

import com.itfenbao.gadmins.core.web.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountQuery extends PageQuery {

    private String name;
    private long roleId = -1;
    private List<String> createdAt;

}
