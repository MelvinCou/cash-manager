package com.cashmanager.server.product.service;

import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.product.exceptions.ProductException;
import com.cashmanager.server.product.service.interfaces.IProductService;
import com.cashmanager.server.product.service.mappers.ProductMapper;
import com.cashmanager.server.shop.entities.Product;
import com.cashmanager.server.shop.repositories.ProductRepository;
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
//                    ProductDto productDto = new ProductDto(product.getId(),product.getName(),product.getPrice(), product.getStock());
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
