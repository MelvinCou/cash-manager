package com.cashmanager.server.account.verification;

import com.cashmanager.server.database.entities.PaymentMethod;
import com.cashmanager.server.database.enums.PaymentMethodType;

public final class CheckVerification {
    /**
     * Private constructor to hide the implicit public one
     */
    private CheckVerification() {
    }

    /**
     * Verify if the payment method is a check
     *
     * @param paymentMethod the payment method to verify
     * @return true if the payment method is a credit card, false otherwise
     */
    public static boolean isACheck(PaymentMethod paymentMethod) {
        return paymentMethod.getType().equals(PaymentMethodType.CHECK);
    }

    /**
     * Verify if the check is cashed
     *
     * @param paymentMethod the payment method to verify
     * @return true if the check is cashed, false otherwise
     */
    public static boolean isCashed(PaymentMethod paymentMethod) {
        return isACheck(paymentMethod) && paymentMethod.getCashed();
    }
}
