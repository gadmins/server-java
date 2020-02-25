package com.itfenbao.gadmins.admin.data.vo;

import com.itfenbao.gadmins.admin.entity.Menu;

public class MenuVO extends Menu {
    private String frontUrl;
    private boolean elink;

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public boolean getElink() {
        return elink;
    }

    public void setElink(boolean elink) {
        this.elink = elink;
    }
}
