package com.hnasc.orderproducts.models.enums;

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
}
