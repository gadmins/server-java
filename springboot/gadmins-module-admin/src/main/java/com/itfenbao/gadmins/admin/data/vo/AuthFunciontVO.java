package com.itfenbao.gadmins.admin.data.vo;

import java.util.Objects;

/**
 * @author itfenbao
 */
public class AuthFunciontVO {
    private Integer id;
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthFunciontVO that = (AuthFunciontVO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthFunciontVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
