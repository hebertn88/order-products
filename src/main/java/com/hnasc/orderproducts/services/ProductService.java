package com.hnasc.orderproducts.services;

import com.hnasc.orderproducts.dtos.product.ProductInsertDTO;
import com.hnasc.orderproducts.dtos.product.ProductPriceDTO;
import com.hnasc.orderproducts.dtos.product.ProductUpdateDTO;
import com.hnasc.orderproducts.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public Product insert(Product Product);

    public Product insert(ProductInsertDTO Product);

    public Product udpate(Long id, ProductUpdateDTO obj);

    public void setPrice(Long id, ProductPriceDTO price);


}
