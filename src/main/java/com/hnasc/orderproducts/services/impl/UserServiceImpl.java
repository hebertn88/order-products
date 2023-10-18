package com.hnasc.orderproducts.services.impl;

import com.hnasc.orderproducts.dtos.user.UserEnableDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.dtos.user.UserUpdateDTO;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.enums.UserRole;
import com.hnasc.orderproducts.models.repositories.UserRepository;
import com.hnasc.orderproducts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void setEnable(Long id, UserEnableDTO obj) {
        var user = repository.findById(id);

        user.ifPresentOrElse(
                u -> {
                    u.setEnabled(obj.status());
                    repository.save(u);
                },
                () -> {
                    throw new IllegalArgumentException("teste");
                }
        );
    }

    @Override
    public User udpate(Long id, UserUpdateDTO obj) {
        var optUser = repository.findById(id);
        if (optUser.isPresent()) {
            var user = optUser.get();
            updateData(user, obj);
            return repository.save(user);
        }
        return null;
    }

    private void updateData(User userDB, UserUpdateDTO obj) {
        UserRole role = UserRole.valueOf(obj.role().toUpperCase());

        userDB.setName(obj.name());
        userDB.setPassword(obj.password());
        userDB.setRole(role);
    }

    public UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(user);
    }

    public List<UserResponseDTO> toUserResponseDTO(List<User> users) {
        return users.stream().map(UserResponseDTO::new).toList();
    }



}
