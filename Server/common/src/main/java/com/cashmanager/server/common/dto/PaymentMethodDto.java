package com.cashmanager.server.common.dto;

import lombok.Data;

@Data
public class PaymentMethodDto {
    private String type;//TODO à changer avec l'enum de Melvin
    private String creditCardNumber;
    private String cvc;
    private String validityDate; //conversion later in LocalDateTime
    private String checkNumber;



}
