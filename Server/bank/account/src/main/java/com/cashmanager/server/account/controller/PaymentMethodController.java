package com.cashmanager.server.account.controller;

import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.database.entities.PaymentMethod;
import com.cashmanager.server.database.enums.AccountState;
import com.cashmanager.server.database.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Controller in charge of managing payment method
 */
@Controller
public class PaymentMethodController {

    private PaymentMethodRepository paymentMethodRepository;

    /**
     * Used to get and validate if PaymentMethod is viable
     *
     * @param paymentMethodDto object
     * @return PaymentMethod object
     */
    public Optional<PaymentMethod> checkPaymentMethodViability(PaymentMethodDto paymentMethodDto) {
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findByCreditCardNumberAndCvc(paymentMethodDto.getCreditCardNumber(), paymentMethodDto.getCvc());

        if (paymentMethod.isPresent()) {
            if (paymentMethod.get().getAccount().getState().equals(AccountState.ACTIVE)) {

                ZonedDateTime currentUtcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));

                LocalDateTime validityDate = paymentMethod.get().getValidityDate();
                ZonedDateTime validityDateUtc = validityDate.atZone(ZoneId.of("UTC"));

                if (validityDateUtc.isBefore(currentUtcDateTime)) { // Everything is ok
                    return paymentMethod;
                } else if (validityDateUtc.isAfter(currentUtcDateTime)) { // case of validityDate is expired
                    // TODO -> Create log, case of validityDate is expired
                    return Optional.empty();
                } else { // case of validityDate is equal to currentDate
                    // TODO -> Create log, case of validityDate is equal to currentDate
                    return Optional.empty();
                }
            } else { // case of Account is INACTIVE
                // TODO -> Create log, case of Account is INACTIVE
                return Optional.empty();
            }
        } else { // case of PaymentMethod isn't found
            // TODO -> Create log, case of PaymentMethod isn't found
            return Optional.empty();
        }
    }
}