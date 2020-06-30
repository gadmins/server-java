package com.itfenbao.gadmins.devops.data.dto.query;

import com.itfenbao.gadmins.core.web.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiQuery extends PageQuery {
    Integer status;
    String apiPath;
    String apiMethod;
    String apiComment;
    String[] createdAt;
}
