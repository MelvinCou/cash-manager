package com.cashmanager.server.product.service.interfaces;

import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.product.exceptions.ProductException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {

    List<ProductDto> getAllProducts() throws ProductException;
    Optional<ProductDto> getProductById(UUID productId);
    Optional<ProductDto> updateProduct(UUID productId, ProductDto product);

}
