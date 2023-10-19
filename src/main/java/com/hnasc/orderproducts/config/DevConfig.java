package com.hnasc.orderproducts.config;

import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.enums.UserRole;
import com.hnasc.orderproducts.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        var foundUser = userRepository.findByUsername("user");
        var foundAdmin = userRepository.findByUsername("admin");

        if (foundUser == null) {
            var u1 = new User("user", "userDev", passwordEncoder.encode("password"), UserRole.USER);
            u1.setEnabled(true);
            userRepository.save(u1);
            System.out.println(u1);
        }
        if (foundAdmin == null) {
            var u2 = new User("admin", "adminDev", passwordEncoder.encode("password"), UserRole.ADMIN);
            u2.setEnabled(true);
            userRepository.save(u2);
            System.out.println(u2);
        }


    }


}
