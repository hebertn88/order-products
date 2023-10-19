package com.hnasc.orderproducts.services.impl;

import com.hnasc.orderproducts.dtos.product.ProductPriceDTO;
import com.hnasc.orderproducts.dtos.product.ProductUpdateDTO;
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

    private Product getProductById(Long id) {
        var optProduct = repository.findById(id);
        return optProduct.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private void updateData(Product productDB, ProductUpdateDTO obj) {
        productDB.setName(obj.name());
        productDB.setDescription(obj.description());
        productDB.setPrice(obj.price());
    }
}