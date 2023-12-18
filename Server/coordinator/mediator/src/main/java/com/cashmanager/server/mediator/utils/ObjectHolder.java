package com.cashmanager.server.mediator.utils;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ObjectHolder {
    private CartDto cart;
    private PaymentMethodDto paymentMethod;
    private BigDecimal amount;
    private String receiver;

    @Override
    public String toString() {
        return "ObjectHolder{" +
                "cart=" + cart +
                ", paymentMethod=" + paymentMethod +
                ", amount=" + amount +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
