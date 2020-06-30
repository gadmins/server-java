package com.itfenbao.gadmins.devops.data.dto.query;

import com.itfenbao.gadmins.core.web.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupQuery extends PageQuery {
    String groupType;
    String[] createdAt;
}
