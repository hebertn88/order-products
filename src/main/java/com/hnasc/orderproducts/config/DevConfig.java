package com.hnasc.orderproducts.config;

import com.hnasc.orderproducts.models.Order;
import com.hnasc.orderproducts.models.OrderItem;
import com.hnasc.orderproducts.models.Product;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.enums.UserRole;
import com.hnasc.orderproducts.models.repositories.OrderItemRepository;
import com.hnasc.orderproducts.models.repositories.OrderRepository;
import com.hnasc.orderproducts.models.repositories.ProductRepository;
import com.hnasc.orderproducts.models.repositories.UserRepository;
import com.hnasc.orderproducts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // users

        var foundUser = userRepository.findByUsername("user");
        var foundAdmin = userRepository.findByUsername("admin");

        if (foundUser == null) {
            var u1 = new User("user", "userDev", passwordEncoder.encode("password"), UserRole.USER);
            u1.setEnabled(true);
            userRepository.save(u1);
            System.out.println("User: " + u1.getUsername() + " , Password: " + u1.getPassword());

            var u2 = new User("admin", "adminDev", passwordEncoder.encode("password"), UserRole.ADMIN);
            u2.setEnabled(true);
            userRepository.save(u2);
            System.out.println("User: " + u2.getUsername() + " , Password: " + u2.getPassword());
        }

        // products

        var p1 = new Product("Cerveja Brahma 350mL", "Cerveja em lata", 2.89);
        var p2 = new Product("Refrigerante Coca-Cola pet 2L", "Refrigerante pet descartável", 7.99);
        var p3 = new Product("Água Mineral Levity Sem Gás 510mL", "Água mineral Sem Gás", 1.29);

        if (productRepository.findByName(p1.getName()).isEmpty()) {
            productRepository.saveAll(Arrays.asList(p1, p2, p3));
        }

        // Order

        var o = new Order((User) foundUser);
        o = orderRepository.save(o);

        p1 = productRepository.findByName(p1.getName()).get();
        p2 = productRepository.findByName(p2.getName()).get();
        p3 = productRepository.findByName(p3.getName()).get();

        var i1 = new OrderItem(o, p1, 2, p1.getPrice());
        var i2 = new OrderItem(o, p2, 1, p2.getPrice());
        var i3 = new OrderItem(o, p3, 3, 0.99);


        orderItemRepository.saveAll(Arrays.asList(i1, i2, i3));

    }


}
