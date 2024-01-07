package com.cashmanager.server.common.dto;

import com.cashmanager.server.common.enumeration.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionDto {
    private UUID id;
    private LocalDateTime date;//conversion later in LocalDateTime
    private TransactionStatus transactionStatus;
    private PaymentMethodDto paymentMethod;
    private BigDecimal amount;
    private String receiver;

    public TransactionDto() {
    }


    public TransactionDto(LocalDateTime date, TransactionStatus transactionStatus, PaymentMethodDto paymentMethod, BigDecimal amount, String receiver) {
        this.date = date;
        this.transactionStatus = transactionStatus;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.receiver = receiver;
    }
}
