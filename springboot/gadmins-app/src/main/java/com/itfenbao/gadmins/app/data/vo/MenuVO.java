package com.itfenbao.gadmins.app.data.vo;

import com.itfenbao.gadmins.app.entity.Menu;

public class MenuVO extends Menu {
    private String frontUrl;

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
}
