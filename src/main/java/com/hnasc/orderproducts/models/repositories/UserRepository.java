package com.hnasc.orderproducts.models.repositories;

import com.hnasc.orderproducts.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, Long> {
    UserDetails findByUsername(String username);
}
