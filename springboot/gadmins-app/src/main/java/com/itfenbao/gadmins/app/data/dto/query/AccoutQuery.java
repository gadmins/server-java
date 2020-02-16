package com.itfenbao.gadmins.app.data.dto.query;

import com.itfenbao.gadmins.core.web.query.PageQuery;
import lombok.Data;

@Data
public class AccoutQuery extends PageQuery {

    private String name;
    private long roleId = -1;

}
