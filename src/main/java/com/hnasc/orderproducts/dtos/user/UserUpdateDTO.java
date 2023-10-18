package com.hnasc.orderproducts.dtos.user;

import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.enums.UserRole;

public record UserUpdateDTO(
        String name,
        String password,
        String role
) {
}
