package com.hnasc.orderproducts.dtos.auth;

import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.enums.UserRole;

public record AuthRegisterDTO(
        String username,
        String name,
        String password
) {
    public User toUser() {
        return new User(
                this.username,
                this.name,
                this.password,
                UserRole.USER
        );

    }
}
