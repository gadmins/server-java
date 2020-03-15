package com.itfenbao.gadmins.core.web.result;

/**
 * json 返回码
 *
 * @author itfenbao
 */
public enum JsonReturnCode {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    PARAMETER_ERROR(400, "参数错误"),
    NOT_LOGIN(401, "请登录"),
    ACCESS_ERROR(403, "禁止访问"),
    NOT_FOUND(404, "未发现"),
    INTERNAL_SERVER_ERROR(500, "内部错误"),
    FAIL_TOKEN(501, "Token过期");

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
