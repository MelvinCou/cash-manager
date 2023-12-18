package com.cashmanager.server.common.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CartDto {
    Map<Integer, ProductDto> listOrderedProducts;
}
