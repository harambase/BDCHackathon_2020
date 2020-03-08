package com.harambase.hackathon.common.constant;

public enum RoleConst {

    USER("ROLE_USER", 0),
    ADMIN("ROLE_ADMIN", 1),
    TEACH("ROLE_TEACH", 2),
    LOGISTIC("ROLE_LOGISTIC", 3),
    SYSTEM("ROLE_SYSTEM", 4),
    STUDENT("ROLE_STUDENT", 5),
    FACULTY("ROLE_FACULTY", 6),
    ADVISOR("ROLE_ADVISOR", 7);

    // 成员变量
    private String roleName;
    private int roleId;

    RoleConst(String roleName, int roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
