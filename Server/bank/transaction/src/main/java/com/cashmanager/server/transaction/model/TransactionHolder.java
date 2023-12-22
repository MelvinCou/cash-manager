package com.cashmanager.server.transaction.model;

import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Object model received from mediator at transaction step.
 * Contain PaymentMethodDto object and BigDecimal to describe amount of transaction
 */
@Getter
@Setter
public class TransactionHolder {

    private PaymentMethodDto paymentMethodDto;
    private BigDecimal amount;

    /**
     * Static function used to setup a transactionDto object
     * @param holder - TransactionHolder object
     * @return new TransactionDto object with amount value
     */
    public static TransactionDto setupTransactionDto(TransactionHolder holder) {
        TransactionDto transaction = new TransactionDto();
        transaction.setAmount(holder.getAmount());
        return transaction;
    }
}
