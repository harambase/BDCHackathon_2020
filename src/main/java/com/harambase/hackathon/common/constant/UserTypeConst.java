package com.harambase.hackathon.common.constant;

public enum UserTypeConst {

    STUDENT("s", 1),
    FACULTY("f", 2),
    ADMINISTRATOR("a", 3);

    // 成员变量
    private String type;
    private int id;

    UserTypeConst(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
