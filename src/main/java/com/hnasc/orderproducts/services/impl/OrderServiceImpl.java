package com.hnasc.orderproducts.services.impl;

import com.hnasc.orderproducts.dtos.order.OrderAddItemDTO;
import com.hnasc.orderproducts.dtos.order.OrderItemResponseDTO;
import com.hnasc.orderproducts.dtos.order.OrderResponseDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.models.Order;
import com.hnasc.orderproducts.models.OrderItem;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.models.repositories.OrderItemRepository;
import com.hnasc.orderproducts.models.repositories.OrderRepository;
import com.hnasc.orderproducts.services.OrderService;
import com.hnasc.orderproducts.services.ProductService;
import com.hnasc.orderproducts.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;
    @Autowired
    ProductService productService;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<Order> findAll() {
        return (List<Order>) repository.findAll();
    }

    @Override
    public Order insert(Order order) throws DataIntegrityViolationException {
        return repository.save(order);
    }
    @Override
    public Order insert(UserDetails user) throws DataIntegrityViolationException {
        return repository.save(new Order((User) user));
    }

    @Override
    public void addItem(Long id, OrderAddItemDTO obj) {
        var order = getOrderById(id);
        var item = productService.getProductById(obj.product());
        var orderItem = new OrderItem(order, item, obj.quantity(), obj.price());
        orderItemRepository.save(orderItem);
    }

    @Override
    public void addItems(Order order, List<OrderAddItemDTO> list) {
        for (OrderAddItemDTO obj : list) {
            var item = productService.getProductById(obj.product());
            var orderItem = new OrderItem(order, item, obj.quantity(), obj.price());
            order.addItem(orderItem);
            repository.save(order);
        }
    }

    public Order getOrderById(Long id) {
        var optOrder = repository.findById(id);
        return optOrder.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public OrderResponseDTO toOrderResponseDTO(Order order) {
        var clientDTO = new UserResponseDTO(order.getClient());
        var items = order.getItems().stream().map(OrderItemResponseDTO::new).toList();
        return new OrderResponseDTO(order.getId(), order.getMoment(), order.getOrderStatus().getStatus(),clientDTO, items, order.getTotal());
    }
    public List<OrderResponseDTO> toOrderResponseDTO(List<Order> orders) {
        List<OrderResponseDTO> resp = new ArrayList<>();
        for (Order order : orders) {
            var clientDTO = new UserResponseDTO(order.getClient());
            var items = order.getItems().stream().map(OrderItemResponseDTO::new).toList();
            resp.add(new OrderResponseDTO(order.getId(), order.getMoment(), order.getOrderStatus().getStatus(), clientDTO, items, order.getTotal()));
        }
        return resp;
    }
}
