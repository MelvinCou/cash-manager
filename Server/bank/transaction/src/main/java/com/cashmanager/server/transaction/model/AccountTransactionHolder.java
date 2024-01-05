package com.cashmanager.server.transaction.model;

import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Object model received from mediator at transaction between account  step.
 * Contain UUID to find store account to credit, UUID to find client account to discredit and BigDecimal to describe amount of transaction.
 */
@Getter
@Setter
public class AccountTransactionHolder {

    private UUID storeAccountId;
    private UUID clientAccountId;
    private BigDecimal amount;

    /**
     * Static function used to setup a transactionDto object
     * @param holder - TransactionHolder object
     * @return new TransactionDto object with amount value
     */
    public static TransactionDto setupTransactionDto(AccountTransactionHolder holder) {
        TransactionDto transaction = new TransactionDto();
        transaction.setAmount(holder.getAmount());
        return transaction;
    }
}
