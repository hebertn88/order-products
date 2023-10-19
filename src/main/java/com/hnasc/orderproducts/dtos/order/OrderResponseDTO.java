package com.hnasc.orderproducts.dtos.order;

import com.hnasc.orderproducts.dtos.product.ProductResponseDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;

import java.time.Instant;
import java.util.List;

public record OrderResponseDTO(Long id, Instant moment, String orderStatus, UserResponseDTO client,
                               List<OrderItemResponseDTO> items, Double total) {
}
