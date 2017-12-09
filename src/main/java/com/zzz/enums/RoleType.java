package com.zzz.enums;

import java.util.Arrays;

/**
 * Created by  on 12/2 0002.
 */
public enum RoleType {

    ADMIN("ADMIN", "管理员"),
    EMPLOYEE("EMPLOYEE", "员工");

    private String code;
    private String name;

    RoleType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static RoleType getByCode(String code) {
        return Arrays.stream(RoleType.values())
                .filter(roleType -> code.equals(roleType.getCode()))
                .findFirst()
                .get();
    }

    public static RoleType getByName(String name) {
        return Arrays.stream(RoleType.values())
                .filter(roleType -> name.equals(roleType.getName()))
                .findFirst()
                .get();
    }

}
