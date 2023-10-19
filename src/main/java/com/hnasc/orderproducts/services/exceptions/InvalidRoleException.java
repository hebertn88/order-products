package com.hnasc.orderproducts.services.exceptions;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String role) {
        super("Invalid Role: " + role);
    }
}
