package com.cashmanager.server.common.dto;

import com.cashmanager.server.common.enumeration.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private String date;
    private CartDto cart;
    private OrderStatus orderStatus;

    public OrderDto() {
    }

    public OrderDto(String date, CartDto cart) {
        this.date = date;
        this.cart = cart;
        this.orderStatus = OrderStatus.PAYMENT_PENDING;
    }
}
