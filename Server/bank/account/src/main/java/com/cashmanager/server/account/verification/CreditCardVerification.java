package com.cashmanager.server.account.verification;

import com.cashmanager.server.common.utils.DateHelper;
import com.cashmanager.server.database.entities.PaymentMethod;
import com.cashmanager.server.database.enums.PaymentMethodType;

import java.time.LocalDateTime;

public final class CreditCardVerification {
    private final static String CARD_REGEX = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$";


    /**
     * Private constructor to hide the implicit public one
     */
    private CreditCardVerification() {
    }

    /**
     * Verify if the payment method is a credit card
     *
     * @param paymentMethod the payment method to verify
     * @return true if the payment method is a credit card, false otherwise
     */
    public static boolean isACreditCard(PaymentMethod paymentMethod) {
        return paymentMethod.getType().equals(PaymentMethodType.CARD);
    }

    /**
     * Verify if the credit card number is valid
     *
     * @param paymentMethod the payment method to verify
     * @return true if the credit card number is valid, false otherwise
     */
    public static boolean verifyCreditCardNumber(PaymentMethod paymentMethod) {
        return isACreditCard(paymentMethod)
                && paymentMethod.getCreditCardNumber().matches(CARD_REGEX);
    }

    /**
     * Verify if the validity date is before today
     *
     * @param paymentMethod the payment method to verify
     * @return true if the validity date is not expired, false otherwise
     */
    public static boolean verifyValidityDate(PaymentMethod paymentMethod) {
        LocalDateTime now = DateHelper.nowYearMonthOnly();
        return isACreditCard(paymentMethod)
                && (paymentMethod.getValidityDate().isAfter(now)
                || paymentMethod.getValidityDate().isEqual(now));
    }

    /**
     * Verify if the CVC is valid
     *
     * @param paymentMethod the payment method to verify
     * @param Cvc           the CVC to verify
     * @return true if the CVC is valid, false otherwise
     */
    public static boolean verifyCvc(PaymentMethod paymentMethod, String Cvc) {
        return isACreditCard(paymentMethod)
                && paymentMethod.getCvc().equals(Cvc);
    }
}
