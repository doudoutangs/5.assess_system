package com.assess.modules.assess.constant;

public enum DeclareStatusEnum {

    WAIT(0,"未申报"),
    NO_APPROVAL(1,"已申报未审批"),
    PASS(2,"审核通过"),
    NO_PASS(3,"审核未通过")
    ;
    private int code;
    private String name;

    DeclareStatusEnum(int code, String name) {
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
