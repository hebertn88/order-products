package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.dtos.auth.AuthLoginDTO;
import com.hnasc.orderproducts.dtos.auth.AuthRegisterDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.security.TokenService;
import com.hnasc.orderproducts.services.UserService;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "login")
    public ResponseEntity<String> login(@RequestBody AuthLoginDTO data) {
        var userCredentialsToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(userCredentialsToken);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(tokenService.TOKEN_PREFIX + token);
    }

    @Transient
    @PostMapping(value = "register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody AuthRegisterDTO data) {
        User user = data.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserResponseDTO userDTO = new UserResponseDTO(userService.insert(user));

        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(userDTO);

    }
}
