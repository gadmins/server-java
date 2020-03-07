package com.itfenbao.gadmins.admin.data.vo;

import lombok.Data;


/**
 * @author itfenbao
 */
@Data
public class FunctionPointVO {
    private Integer id;
    private String code;
    private String desc;
    private String txt;
    private String apiUrl;
    private String apiMethod;
    private String schema;
}
