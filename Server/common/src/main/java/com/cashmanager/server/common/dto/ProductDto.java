package com.cashmanager.server.common.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String productUrl;
    private int stock;

    public ProductDto(UUID id, String name, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public ProductDto() {

    }
}
