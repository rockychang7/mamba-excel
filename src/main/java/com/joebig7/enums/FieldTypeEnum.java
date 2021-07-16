package com.joebig7.enums;

/**
 * @Author JoeBig7
 * @date 2021/7/16 17:04:01
 */
public enum FieldTypeEnum {
    INTEGER("integer"),
    STRING("string");

    private String type;

    FieldTypeEnum(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
