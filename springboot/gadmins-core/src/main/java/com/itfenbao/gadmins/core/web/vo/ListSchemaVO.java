package com.itfenbao.gadmins.core.web.vo;

import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 列表结构
 *
 * @author itfenbao
 */
public class ListSchemaVO {
    public final static class Column {
        private String title;
        private String dataIndex;
        private String valueType;
        private Integer width;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDataIndex() {
            return dataIndex;
        }

        public void setDataIndex(String dataIndex) {
            this.dataIndex = dataIndex;
        }

        public String getValueType() {
            return valueType;
        }

        public void setValueType(String valueType) {
            this.valueType = valueType;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }
    }

    private List<Column> columns = new ArrayList<>();

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addColumn(Field field) {
        ApiModelProperty property = field.getAnnotation(ApiModelProperty.class);
        if (property != null) {
            Column c = new Column();
            c.title = property.value();
            c.dataIndex = field.getName();
            // FIXME: 字段类型
            c.valueType = "text";
            this.columns.add(c);
        }
    }

}
