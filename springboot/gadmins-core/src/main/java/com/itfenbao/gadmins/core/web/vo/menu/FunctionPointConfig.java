package com.itfenbao.gadmins.core.web.vo.menu;

public class FunctionPointConfig {

    private Integer funcId;

    private String url;
    private String method;
    private String schema;

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        return "FunctionPointConfig{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", schema='" + schema + '\'' +
                '}';
    }
}
