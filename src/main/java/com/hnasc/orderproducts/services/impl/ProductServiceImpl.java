package com.hnasc.orderproducts.services.impl;

import com.hnasc.orderproducts.dtos.product.ProductInsertDTO;
import com.hnasc.orderproducts.dtos.product.ProductPriceDTO;
import com.hnasc.orderproducts.dtos.product.ProductResponseDTO;
import com.hnasc.orderproducts.dtos.product.ProductUpdateDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.models.Product;
import com.hnasc.orderproducts.models.repositories.ProductRepository;
import com.hnasc.orderproducts.services.ProductService;
import com.hnasc.orderproducts.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product insert(Product product) throws DataIntegrityViolationException {
        return repository.save(product);
    }

    @Override
    public Product insert(ProductInsertDTO obj) throws DataIntegrityViolationException {
        var product = new Product(obj.name(), obj.description(), obj.price());
        return repository.save(product);
    }

    @Override
    public Product udpate(Long id, ProductUpdateDTO obj) {
        var product = getProductById(id);
        updateData(product, obj);
        return repository.save(product);
    }

    @Override
    public void setPrice(Long id, ProductPriceDTO price) {
        var product = getProductById(id);
        product.setPrice(price.price());
        repository.save(product);
    }

    public Product getProductById(Long id) {
        var optProduct = repository.findById(id);
        return optProduct.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(product.getId(), product.getName(), product.getDescription());
    }

    @Override
    public List<ProductResponseDTO> toProductResponseDTO(List<Product> products) {
        return products.stream().map(ProductResponseDTO::new).toList();
    }

    private void updateData(Product productDB, ProductUpdateDTO obj) {
        productDB.setName(obj.name());
        productDB.setDescription(obj.description());
    }
}
