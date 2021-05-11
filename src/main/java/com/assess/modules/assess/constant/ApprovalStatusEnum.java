package com.assess.modules.assess.constant;

public enum ApprovalStatusEnum {

    PASS(0,"通过"),
    NO_PASS(1,"未通过")
    ;
    private int code;
    private String name;

    ApprovalStatusEnum(int code, String name) {
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
