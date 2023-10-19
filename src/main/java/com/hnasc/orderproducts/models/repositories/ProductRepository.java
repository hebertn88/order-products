package com.hnasc.orderproducts.models.repositories;

import com.hnasc.orderproducts.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
