package com.hnasc.orderproducts.dtos.user;

import com.hnasc.orderproducts.models.User;

import java.io.Serializable;

public record UserResponseDTO(Long id, String username, String name) implements Serializable {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getUsername(), user.getName());
    }
}
