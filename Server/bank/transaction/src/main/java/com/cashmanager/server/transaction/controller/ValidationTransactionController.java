package com.cashmanager.server.transaction.controller;

import com.cashmanager.server.account.controller.AccountController;
import com.cashmanager.server.account.controller.PaymentMethodController;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.database.entity.PaymentMethod;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Controller in charge of dispatching request for returning the validation of a bank transaction
 */
@Controller
public class ValidationTransactionController {

    private final PaymentMethodController paymentMethodController = new PaymentMethodController();
    private final AccountController accountController = new AccountController();

    /**
     * Used to check if transaction is valid
     * @param paymentMethodDto  - payment method
     * @param transactionDto    - transaction to pay
     */
    public void checkTransaction(PaymentMethodDto paymentMethodDto, TransactionDto transactionDto) {

        Optional<PaymentMethod> paymentMethod = paymentMethodController.checkPaymentMethodViability(paymentMethodDto);
        if (paymentMethod.isPresent()) {
            if (accountController.checkAccountValidity(paymentMethod.get().getAccount(), transactionDto)) {
                // TODO -> Set transaction to OK
            } else {
                // TODO -> Set transaction to STOPPED
            }
        } else {
            // TODO -> Create log, case of paymentMethod is empty, break and notify the transaction
            // TODO -> Set transaction to STOPPED
        }
    }
}