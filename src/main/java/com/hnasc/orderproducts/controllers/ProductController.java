package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.models.Product;
import com.hnasc.orderproducts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        var products = productService.findAll();
        return ResponseEntity.ok(products);
    }
}
