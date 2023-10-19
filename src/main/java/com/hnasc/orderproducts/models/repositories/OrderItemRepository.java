package com.hnasc.orderproducts.models.repositories;

import com.hnasc.orderproducts.models.OrderItem;
import com.hnasc.orderproducts.models.pk.OrderItemPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPK> {
}
