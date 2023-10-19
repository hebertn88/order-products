package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.dtos.user.UserEnableDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.dtos.user.UserRoleDTO;
import com.hnasc.orderproducts.dtos.user.UserUpdateDTO;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        var users = userService.findAll();
        return ResponseEntity.ok(userService.toUserResponseDTO(userService.findAll()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserUpdateDTO obj) {
        obj = new UserUpdateDTO(obj.name(), passwordEncoder.encode(obj.password()), obj.role());
        User user = userService.udpate(id, obj);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UserResponseDTO> enable(@PathVariable Long id, @RequestBody UserEnableDTO obj) {
        userService.setEnable(id, obj);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/credentials")
    public ResponseEntity<UserResponseDTO> setRoles(@PathVariable Long id, @RequestBody UserRoleDTO obj) {
        userService.setRole(id, obj);
        return ResponseEntity.ok().build();
    }


}
