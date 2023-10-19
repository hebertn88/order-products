package com.hnasc.orderproducts.dtos.order;

import com.hnasc.orderproducts.dtos.product.ProductResponseDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.models.OrderItem;

import java.time.Instant;
import java.util.List;

public record OrderItemResponseDTO(Integer quantity, Double price, ProductResponseDTO product, Double subtotal) {
    public OrderItemResponseDTO(OrderItem item) {
        this(item.getQuantity(), item.getPrice(), new ProductResponseDTO(item.getProduct()), item.getSubTotal());
    }
}
