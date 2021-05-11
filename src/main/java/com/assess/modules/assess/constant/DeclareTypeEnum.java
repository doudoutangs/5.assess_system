package com.assess.modules.assess.constant;

public enum DeclareTypeEnum {

    WHOLE(0,"整体"),
    INDIVIDUAL(1,"单项")
    ;
    private int code;
    private String name;

    DeclareTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
