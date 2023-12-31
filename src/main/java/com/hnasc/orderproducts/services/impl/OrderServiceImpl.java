package com.hnasc.orderproducts.services.impl;

import com.hnasc.orderproducts.dtos.order.OrderAddItemDTO;
import com.hnasc.orderproducts.dtos.order.OrderItemResponseDTO;
import com.hnasc.orderproducts.dtos.order.OrderResponseDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.models.Order;
import com.hnasc.orderproducts.models.OrderItem;
import com.hnasc.orderproducts.models.Product;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Order getOrderById(Long id) {
        var optOrder = repository.findById(id);
        return optOrder.orElseThrow(() -> new ResourceNotFoundException(id));
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
    public void delete(Order order) {
        repository.delete(order);
    }

    @Override
    public void addItem(Long id, OrderAddItemDTO obj) throws DataIntegrityViolationException {
        var order = getOrderById(id);
        var item = productService.getProductById(obj.product());

        if (checkIfItemExistsInOrder(order, item).isPresent()) {
            throw new DataIntegrityViolationException("The item is already on the order. Id: " + item.getId());
        }

        var orderItem = new OrderItem(order, item, obj.quantity(), obj.price());
        orderItemRepository.save(orderItem);
    }

    @Override
    public void addItems(Order order, List<OrderAddItemDTO> list) throws DataIntegrityViolationException {
        for (OrderAddItemDTO obj : list) {
            var item = productService.getProductById(obj.product());

            if (checkIfItemExistsInOrder(order, item).isPresent()) {
                throw new DataIntegrityViolationException("The item is already on the order. Id: " + item.getId());
            }

            var orderItem = new OrderItem(order, item, obj.quantity(), obj.price());
            order.addItem(orderItem);
            repository.save(order);
        }
    }

    public void deleteItem(Order order, Product item) {
        var orderItem = checkIfItemExistsInOrder(order, item).orElseThrow(() -> new ResourceNotFoundException(item.getId()));
        order.removeItem(orderItem);
        orderItemRepository.delete(orderItem);
    }


    public OrderResponseDTO toOrderResponseDTO(Order order) {
        var clientDTO = new UserResponseDTO(order.getClient());
        var items = order.getItems().stream().map(OrderItemResponseDTO::new).toList();
        return new OrderResponseDTO(order.getId(), order.getMoment(), order.getOrderStatus().getStatus(), clientDTO, items, order.getTotal());
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

    private Optional<OrderItem> checkIfItemExistsInOrder(Order order, Product item) {
        return order.getItems().stream().filter(i -> i.getProduct() == item).findFirst();
    }
}
