package com.hnasc.orderproducts.services;

import com.hnasc.orderproducts.dtos.order.OrderAddItemDTO;
import com.hnasc.orderproducts.dtos.order.OrderResponseDTO;
import com.hnasc.orderproducts.models.Order;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface OrderService {
    public List<Order> findAll();

    public Order insert(Order order);
    public Order insert(UserDetails user);

    public void addItem(Long id, OrderAddItemDTO obj);

    public void addItems(Order order, List<OrderAddItemDTO> obj);
    public OrderResponseDTO toOrderResponseDTO(Order order);
    public List<OrderResponseDTO> toOrderResponseDTO(List<Order> order);
    public Order getOrderById(Long id);


}
