package com.hnasc.orderproducts.services;

import com.hnasc.orderproducts.dtos.user.UserEnableDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.dtos.user.UserRoleDTO;
import com.hnasc.orderproducts.dtos.user.UserUpdateDTO;
import com.hnasc.orderproducts.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> findAll();

    public User findById(Long id);

    public UserDetails loadUserByUsername(String username);

    public User insert(User user);

    public User udpate(Long id, UserUpdateDTO obj);

    public void setEnable(Long id, UserEnableDTO status);

    public void setRole(Long id, UserRoleDTO obj);

    public UserResponseDTO toUserResponseDTO(User user);

    public List<UserResponseDTO> toUserResponseDTO(List<User> users);

}
