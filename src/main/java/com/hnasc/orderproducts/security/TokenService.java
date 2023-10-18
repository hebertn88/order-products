package com.hnasc.orderproducts.security;

import com.hnasc.orderproducts.dtos.security.ParsedClaimsDTO;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.services.UserService;
import io.jsonwebtoken.Jwts;


import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;


@Service
public class TokenService {
    @Value("${api.security.token.secret_key}")
    private String SECRET_KEY;
    @Value("${api.security.token.expiration_milliseconds}")
    private Long EXPIRATION;

    public final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private UserService userService;

    public String generateToken(User user) {
        Key signKey = this.getSignKey();
        Date expiration = this.generateExpiration();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuer("hnasc:order-items")
                .expiration(expiration)
                .signWith(signKey)
                .compact();
    }

    public UserDetails validateToken(String token) {
        ParsedClaimsDTO claims = this.parseToken(token);
        var validIssuer = claims.iss().equals("hnasc:order-items");
        var validExpiration = claims.exp().after(new Date());
        var validUser = userService.loadUserByUsername(claims.sub()).isEnabled();

        if (validIssuer && validExpiration && validUser) {
            return userService.loadUserByUsername(claims.sub());
        }
        return null;
    }

    private ParsedClaimsDTO parseToken(String token) {
        Key signKey = this.getSignKey();
        var claims =  Jwts.parser()
                .verifyWith((SecretKey) signKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return new ParsedClaimsDTO(claims.getSubject(), claims.getIssuer(), claims.getExpiration());
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    }

    private Date generateExpiration() {
        return Date.from(Instant.now().plusMillis(EXPIRATION));
    }
}
