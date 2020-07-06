package com.itfenbao.gadmins.core.web.data.dto.param.db;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/6 9:58 上午
 */
public class CommonColumnParam {
    String columnType;
    Integer valueLength;
    boolean unsigned;
    String defValue;
    boolean isNull;
    String columnComment;

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Integer getValueLength() {
        return valueLength;
    }

    public void setValueLength(Integer valueLength) {
        this.valueLength = valueLength;
    }

    public boolean isUnsigned() {
        return unsigned;
    }

    public void setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
    }

    public String getDefValue() {
        return defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
