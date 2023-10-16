package com.hnasc.orderproducts.repositories;

import com.hnasc.orderproducts.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    UserDetails findByUsername(String username);
}
