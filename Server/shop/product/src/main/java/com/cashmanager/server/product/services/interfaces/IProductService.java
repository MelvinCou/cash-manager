package com.cashmanager.server.product.services.interfaces;

import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.product.exceptions.ProductException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {

    /**
     * Function that recuperate the products available in the database, via the ProductRepository
     * @return List of ProductDto
     * @throws ProductException if there is an error when communicating with the database
     */
    List<ProductDto> getAllProducts() throws ProductException;
    Optional<ProductDto> getProductById(UUID productId);
    Optional<ProductDto> updateProduct(UUID productId, ProductDto product);

}
