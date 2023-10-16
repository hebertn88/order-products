package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.models.UserRole;
import com.hnasc.orderproducts.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "user-roles")
public class UserRoleController {
    @Autowired
    UserRoleService roleService;
    @GetMapping
    public ResponseEntity<List<UserRole>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }
}
