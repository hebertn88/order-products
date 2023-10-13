package com.hnasc.orderproducts.user.enums;

import javax.management.relation.Role;
import java.util.Objects;

public enum UserRoleEnum {
    USER(0),
    MANAGER(1),
    ADMIN(2);

    public final Integer code;
    private UserRoleEnum(Integer code) {
        this.code = code;
    }
    public static UserRoleEnum valueOf(Integer code) {
        for (UserRoleEnum role : values()) {
            if (Objects.equals(role.code, code)) {
                return role;
            }
        }
        return null;
    }

}
