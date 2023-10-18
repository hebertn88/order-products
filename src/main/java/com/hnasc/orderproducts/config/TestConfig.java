package com.hnasc.orderproducts.config;

import com.hnasc.orderproducts.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
           /* var r1 = roleService.create(new UserRoleOld("user"));
           r1.ifPresent(role -> {
               var user = new User("user", "hebert", passwordEncoder.encode("password"), role);
               var u = userRepository.save(user);
               System.out.println(u);
           });

           var r2 = roleService.create(new UserRoleOld("admin"));
           r2.ifPresent(role -> {
               var user = new User("admin", "hebertAdmin", passwordEncoder.encode("password"), role);
               var u = userRepository.save(user);
               System.out.println(u);
           }); */

    }


}
