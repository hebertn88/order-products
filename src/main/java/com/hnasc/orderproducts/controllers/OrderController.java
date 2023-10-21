package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.dtos.order.OrderAddItemDTO;
import com.hnasc.orderproducts.dtos.order.OrderDeleteItemDTO;
import com.hnasc.orderproducts.dtos.order.OrderItemResponseDTO;
import com.hnasc.orderproducts.dtos.order.OrderResponseDTO;
import com.hnasc.orderproducts.dtos.product.ProductInsertDTO;
import com.hnasc.orderproducts.dtos.product.ProductPriceDTO;
import com.hnasc.orderproducts.dtos.product.ProductUpdateDTO;
import com.hnasc.orderproducts.models.Order;
import com.hnasc.orderproducts.models.Product;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.services.OrderService;

import com.hnasc.orderproducts.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        var orders = orderService.findAll();
        return ResponseEntity.ok(orderService.toOrderResponseDTO(orders));

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable Long id) {
        var order = orderService.getOrderById(id);
        return ResponseEntity.ok(orderService.toOrderResponseDTO(order));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderResponseDTO> insert(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<OrderAddItemDTO> obj) {
        var order = new Order((User) userDetails);
        order = orderService.insert(order);
        orderService.addItems(order, obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(location).body(orderService.toOrderResponseDTO(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> delete(@PathVariable Long id) {
        var order = orderService.getOrderById(id);
        orderService.delete(order);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<OrderResponseDTO> addItems(@PathVariable Long id, @RequestBody List<OrderAddItemDTO> obj) {
        var order = orderService.getOrderById(id);
        orderService.addItems(order, obj);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/items")
    public ResponseEntity<OrderResponseDTO> deleteItem(@PathVariable Long id, @RequestBody OrderDeleteItemDTO id_product) {
        var order = orderService.getOrderById(id);
        var product = productService.getProductById(id_product.id_product());
        orderService.deleteItem(order, product);

        return ResponseEntity.ok().build();
    }

}
