package com.hnasc.orderproducts.models.repositories;

import com.hnasc.orderproducts.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByName(String name);

}
