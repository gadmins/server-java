package com.itfenbao.gadmins.admin.data.vo;


public class AuthFunctionPointVO extends AuthFunciontVO {
    private String btnGroup;
    private String title;
    private String apiUrl;
    private String apiMethod;
    private String frontUrl;


    public String getBtnGroup() {
        return btnGroup;
    }

    public void setBtnGroup(String btnGroup) {
        this.btnGroup = btnGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
}
