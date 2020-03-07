package com.itfenbao.gadmins.core.web.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormSchemaVO {
    public final static class Form {
        private String type = "object";
        private Map<String, FormItem> properties = new LinkedHashMap<>();

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Map<String, FormItem> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, FormItem> properties) {
            this.properties = properties;
        }
    }

    public final static class FormItem {
        private String type;
        private String title;
        private Boolean required = true;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Boolean getRequired() {
            return required;
        }

        public void setRequired(Boolean required) {
            this.required = required;
        }
    }

    private Form schema = new Form();

    public Form getSchema() {
        return schema;
    }

    public void setSchema(Form schema) {
        this.schema = schema;
    }

    public void addFormItem(Field field) {
        ApiModelProperty property = field.getAnnotation(ApiModelProperty.class);
        if (property != null) {
            FormItem formItem = new FormItem();
            formItem.title = property.value();
            formItem.type = getType(field.getType());
            formItem.required = field.getAnnotation(NotNull.class) != null;
            this.schema.properties.put(field.getName(), formItem);
        }
    }

    private String getType(Class typeClass) {
        if (typeClass == Integer.class || typeClass == Long.class) {
            return "number";
        } else if (typeClass == Boolean.class) {
            return "checkbox";
        } else if (typeClass == LocalDateTime.class) {
            return "date";
        } else {
            return "string";
        }
    }

}
