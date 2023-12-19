package com.cashmanager.server.common.dto;

import com.cashmanager.server.common.enumeration.PaymentMethodType;
import lombok.Data;

@Data
public class PaymentMethodDto {
    private PaymentMethodType type;
    private String creditCardNumber;
    private String cvc;
    private String validityDate; //conversion later in LocalDateTime
    private int checkNumber;
}
