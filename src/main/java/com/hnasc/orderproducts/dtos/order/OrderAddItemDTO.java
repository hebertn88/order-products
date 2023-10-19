package com.hnasc.orderproducts.dtos.order;

public record OrderAddItemDTO(Long product, Integer quantity, Double price) {
}
