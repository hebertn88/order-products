package com.hnasc.orderproducts.models.repositories;

import com.hnasc.orderproducts.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
