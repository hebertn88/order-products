package com.hnasc.orderproducts.services;

import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.UserRole;
import com.hnasc.orderproducts.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository repository;

    public List<UserRole> findAll() {
        return (List<UserRole>) repository.findAll();
    }

    public Optional<UserRole> create(UserRole role){
        try {
            return Optional.of(repository.save(role));
        } catch (DataIntegrityViolationException e) {
            System.out.println("Register already exists: " + role);
            return Optional.empty();
        }
    }
}
