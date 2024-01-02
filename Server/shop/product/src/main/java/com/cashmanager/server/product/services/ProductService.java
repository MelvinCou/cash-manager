package com.cashmanager.server.product.services;

import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.product.exceptions.ProductException;
import com.cashmanager.server.product.services.interfaces.IProductService;
import com.cashmanager.server.product.services.mappers.ProductMapper;

import com.cashmanager.server.shop_database.entities.Product;
import com.cashmanager.server.shop_database.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() throws ProductException {
        try{
            List<Product> products = productRepository.findAll();
            List<ProductDto> productDtos = new ArrayList<>();
            if(products.isEmpty()){
                return productDtos;
            }
            else{
                products.forEach(product -> {
                    ProductDto productDto = ProductMapper.INSTANCE.productToProductDto(product);
                    productDtos.add(productDto);
                });
                return productDtos;
            }
        }catch (Exception e){
            throw new ProductException(e.getMessage());
        }

    }

    @Override
    public Optional<ProductDto> getProductById(UUID productId) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDto> updateProduct(UUID productId, ProductDto product) {
        return Optional.empty();
    }
}
