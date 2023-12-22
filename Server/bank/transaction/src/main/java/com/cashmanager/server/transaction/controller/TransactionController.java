package com.cashmanager.server.transaction.controller;

import com.cashmanager.server.account.service.AccountService;
import com.cashmanager.server.account.service.PaymentMethodService;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.entity.PaymentMethod;
import com.cashmanager.server.transaction.model.TransactionHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controller in charge of dispatching request for returning the validation of a bank transaction
 */
@RestController
public class TransactionController { // TODO -> TransactionController

    private PaymentMethodService paymentMethodService;
    private AccountService accountService;

    /**
     * Starting point from mediator for payment method and transaction steps
     * @param transactionHolder - Object containing payment method and amount of transaction
     */
    @PostMapping("/transaction")
    public void checkTransaction(@RequestBody TransactionHolder transactionHolder) {

        PaymentMethodDto paymentMethodDto = transactionHolder.getPaymentMethodDto();
        TransactionDto transactionDto = TransactionHolder.setupTransactionDto(transactionHolder);

        Optional<PaymentMethod> paymentMethod = paymentMethodService.checkPaymentMethodViability(paymentMethodDto, transactionDto);
        if (paymentMethod.isPresent()) {
            if (accountService.checkAccountValidity(paymentMethod.get().getAccount(), transactionDto)) {
                // TODO -> Create log
                transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_ACCEPTED);
            } else {
                // TODO -> Create log
                transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
            }
        } else {
            // TODO -> Create log, case of paymentMethod is empty, break and notify the transaction
            transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
        }
    }
}