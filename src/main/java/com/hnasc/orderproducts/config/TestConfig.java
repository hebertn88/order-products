package com.hnasc.orderproducts.config;

import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.UserRole;
import com.hnasc.orderproducts.repositories.UserRepository;
import com.hnasc.orderproducts.repositories.UserRoleRepository;
import com.hnasc.orderproducts.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRoleService roleService;
    @Autowired
    private UserRoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
           var r1 = roleService.create(new UserRole("user"));
           r1.ifPresent(role -> {
               var user = new User("user", "hebert", passwordEncoder.encode("password"), role);
               var u = userRepository.save(user);
               System.out.println(u);
           });

           var r2 = roleService.create(new UserRole("admin"));
           r2.ifPresent(role -> {
               var user = new User("admin", "hebertAdmin", passwordEncoder.encode("password"), role);
               var u = userRepository.save(user);
               System.out.println(u);
           });

    }


}
