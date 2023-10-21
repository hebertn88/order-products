package com.hnasc.orderproducts.services;

import com.hnasc.orderproducts.dtos.order.OrderAddItemDTO;
import com.hnasc.orderproducts.dtos.order.OrderResponseDTO;
import com.hnasc.orderproducts.models.Order;
import com.hnasc.orderproducts.models.Product;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface OrderService {
    public List<Order> findAll();
    public Order getOrderById(Long id);

    public Order insert(Order order);
    public Order insert(UserDetails user);
    public void delete(Order order);

    public void addItem(Long id, OrderAddItemDTO obj);

    public void addItems(Order order, List<OrderAddItemDTO> obj);
    public void deleteItem(Order order, Product item);
    public OrderResponseDTO toOrderResponseDTO(Order order);
    public List<OrderResponseDTO> toOrderResponseDTO(List<Order> order);


}
