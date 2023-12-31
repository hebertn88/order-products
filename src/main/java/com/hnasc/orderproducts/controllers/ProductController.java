package com.hnasc.orderproducts.controllers;

import com.hnasc.orderproducts.dtos.product.ProductInsertDTO;
import com.hnasc.orderproducts.dtos.product.ProductPriceDTO;
import com.hnasc.orderproducts.dtos.product.ProductUpdateDTO;
import com.hnasc.orderproducts.models.Product;
import com.hnasc.orderproducts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        var product = productService.getProductById(id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location).body(product);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductInsertDTO obj) {
        var product = productService.insert(obj);
        return ResponseEntity.ok(product);
    }

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
}
