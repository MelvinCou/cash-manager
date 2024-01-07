package com.cashmanager.server.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CartDto {
    private Map<Integer, ProductDto> listOrderedProducts;
}
