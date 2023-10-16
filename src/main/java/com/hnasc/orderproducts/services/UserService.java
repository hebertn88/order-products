package com.hnasc.orderproducts.services;

import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }
}
