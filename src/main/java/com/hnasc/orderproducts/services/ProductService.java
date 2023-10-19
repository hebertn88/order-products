package com.hnasc.orderproducts.services;

import com.hnasc.orderproducts.dtos.product.ProductPriceDTO;
import com.hnasc.orderproducts.dtos.product.ProductUpdateDTO;
import com.hnasc.orderproducts.models.Product;
import com.hnasc.orderproducts.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public Product insert(Product Product);

    public Product udpate(Long id, ProductUpdateDTO obj);

    public void setPrice(Long id, ProductPriceDTO price);


}
