package com.cashmanager.server.transaction.controller;

import com.cashmanager.server.account.controller.PaymentMethodController;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.database.entities.PaymentMethod;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Controller in charge of dispatching request for returning the validation of a bank transaction
 */
@Controller
public class ValidationTransactionController {

    private final PaymentMethodController paymentMethodController = new PaymentMethodController();

    public void checkTransaction(PaymentMethodDto paymentMethodDto) {

        Optional<PaymentMethod> paymentMethod = paymentMethodController.checkPaymentMethodViability(paymentMethodDto);
        if (paymentMethod.isPresent()) {
            // TODO -> Check other part ?
            System.out.println("paymentMethod.isPresent()");
        } else {
            // TODO -> Create log, case of paymentMethod is empty, break and notify the transaction
            System.out.println("!paymentMethod.isPresent()");
        }
    }
}