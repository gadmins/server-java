package com.itfenbao.gadmins.core.web;

public enum JsonReturnCode {
    PARAMETER_ERROR(400, "参数错误"),
    NOT_LOGIN(401, "未登录"),
    SUCCESS(200, "成功"),
    FAIL(500, "内部失败"),
    ACCESS_ERROR(403, "禁止访问"),
    NOT_FOUND(404, "未发现");
    private Integer code;
    private String desc;

    JsonReturnCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
