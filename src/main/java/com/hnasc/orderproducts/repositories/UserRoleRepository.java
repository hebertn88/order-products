package com.hnasc.orderproducts.repositories;

import com.hnasc.orderproducts.models.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository extends CrudRepository<UserRole, UUID> {
    public UserRole findByDescription(String description);
}
