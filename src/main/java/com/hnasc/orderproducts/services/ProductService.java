package com.hnasc.orderproducts.services;

import com.hnasc.orderproducts.dtos.product.ProductInsertDTO;
import com.hnasc.orderproducts.dtos.product.ProductPriceDTO;
import com.hnasc.orderproducts.dtos.product.ProductResponseDTO;
import com.hnasc.orderproducts.dtos.product.ProductUpdateDTO;
import com.hnasc.orderproducts.dtos.user.UserResponseDTO;
import com.hnasc.orderproducts.models.Product;
import com.hnasc.orderproducts.models.User;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public Product insert(Product product);

    public Product insert(ProductInsertDTO product);

    public Product udpate(Long id, ProductUpdateDTO obj);

    public void setPrice(Long id, ProductPriceDTO price);
    public Product getProductById(Long id);

    public ProductResponseDTO toProductResponseDTO(Product product);
    public List<ProductResponseDTO> toProductResponseDTO(List<Product> products);


}
