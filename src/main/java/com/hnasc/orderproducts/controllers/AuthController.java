package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.dtos.AuthLoginDTO;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping(value = "login")
    public ResponseEntity<String> login(@RequestBody AuthLoginDTO data) {
        var userCredentialsToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(userCredentialsToken);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        System.out.println(token);
        System.out.println(tokenService.validateToken(token));

        return ResponseEntity.ok(token);
    }
}
