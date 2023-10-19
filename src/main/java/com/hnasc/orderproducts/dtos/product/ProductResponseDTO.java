package com.hnasc.orderproducts.dtos.product;

import com.hnasc.orderproducts.models.Product;

public record ProductResponseDTO(Long id, String name, String description) {
    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription());
    }
}
