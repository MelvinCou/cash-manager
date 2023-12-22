package com.cashmanager.server.product.service.mappers;

import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.shop.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
}
