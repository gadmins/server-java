package com.itfenbao.gadmins.devops.data.dto.query;

import com.itfenbao.gadmins.core.web.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DbQuery extends PageQuery {
    String dbName;
    String name;
    String comment;
    String[] createdAt;
}
