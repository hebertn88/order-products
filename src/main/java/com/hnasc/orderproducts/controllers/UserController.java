package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.UserRole;
import com.hnasc.orderproducts.services.UserRoleService;
import com.hnasc.orderproducts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
