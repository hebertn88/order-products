package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.dtos.order.OrderAddItemDTO;
import com.hnasc.orderproducts.dtos.order.OrderItemResponseDTO;
import com.hnasc.orderproducts.dtos.order.OrderResponseDTO;
import com.hnasc.orderproducts.dtos.product.ProductInsertDTO;
import com.hnasc.orderproducts.dtos.product.ProductPriceDTO;
import com.hnasc.orderproducts.dtos.product.ProductUpdateDTO;
import com.hnasc.orderproducts.models.Order;
import com.hnasc.orderproducts.models.Product;
import com.hnasc.orderproducts.models.User;
import com.hnasc.orderproducts.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        var orders = orderService.findAll();
        return ResponseEntity.ok(orderService.toOrderResponseDTO(orders));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> insert(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<OrderAddItemDTO> obj) {

        var order = new Order((User) userDetails);
        order = orderService.insert(order);
        orderService.addItems(order, obj);
        return ResponseEntity.ok(orderService.toOrderResponseDTO(order));
    }

    // TODO : add items
/*
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductUpdateDTO obj) {
        productService.udpate(id, obj);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductPriceDTO obj) {
        productService.setPrice(id, obj);
        return ResponseEntity.ok().build();
    }

 */
}
