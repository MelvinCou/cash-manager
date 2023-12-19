package com.cashmanager.server.account.controller;

import com.cashmanager.server.account.verification.CheckVerification;
import com.cashmanager.server.account.verification.CreditCardVerification;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.database.entity.PaymentMethod;
import com.cashmanager.server.database.repository.PaymentMethodRepository;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Controller in charge of managing and checking payment method
 */
@Controller
public class PaymentMethodController {

    private PaymentMethodRepository paymentMethodRepository;
    private CreditCardVerification creditCardVerification;

    /**
     * Used to get and validate if PaymentMethod is viable
     *
     * @param paymentMethodDto object
     * @return PaymentMethod object
     */
    public Optional<PaymentMethod> checkPaymentMethodViability(PaymentMethodDto paymentMethodDto) {

        Optional<PaymentMethod> paymentMethod = Optional.empty();
        if (CreditCardVerification.isACreditCard(paymentMethodDto)) {

            paymentMethod = paymentMethodRepository.findByCreditCardNumberAndCvc(paymentMethodDto.getCreditCardNumber(), paymentMethodDto.getCvc());
            if (paymentMethod.isEmpty()) {
                // TODO -> Create log, case of paymentMethod credit card isn't found
            }
        } else if (CheckVerification.isACheck(paymentMethodDto)) {

            paymentMethod = paymentMethodRepository.findByCheckNumber(paymentMethodDto.getCheckNumber());
            if (paymentMethod.isEmpty()) {
                // TODO -> Create log, case of paymentMethod check isn't found
            }
        } else {
            // TODO -> Create log, case of PaymentMethod isn't check or card
        }
        return paymentMethod;
    }
}