package com.cashmanager.server.product.services.mappers;

import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.shop_database.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * Function that convert the entity Product to the ProductDto
     * @param product : the product that will be converted
     * @return productDto that result from the conversion
     */
    ProductDto productToProductDto(Product product);

    /**
     * Function that convert the dto ProductDto to the entity Product
     * @param productDto : the dto that will be converted
     * @return product that result from the conversion
     */
    Product productDtoToProduct(ProductDto productDto);
}
