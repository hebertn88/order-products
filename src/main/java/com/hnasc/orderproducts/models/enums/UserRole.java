package com.hnasc.orderproducts.models.enums;

import com.hnasc.orderproducts.services.exceptions.InvalidRoleException;

import java.util.Objects;

public enum UserRole {
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole fromString(String role) {
        role = role.toUpperCase();
        for (UserRole r : UserRole.values()) {
            if (Objects.equals(r.getRole(), role)) {
                return r;
            }
        }
        throw new InvalidRoleException(role);
    }
}
