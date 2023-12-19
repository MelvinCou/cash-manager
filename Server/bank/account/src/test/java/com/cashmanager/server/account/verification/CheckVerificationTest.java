package com.cashmanager.server.account.verification;

import com.cashmanager.server.common.utils.DateHelper;
import com.cashmanager.server.database.entity.PaymentMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckVerificationTest {

    @Test
    void isACheck() {
        PaymentMethod paymentMethod = PaymentMethod.createCheck(null, 123456789, false);
        assertTrue(CheckVerification.isACheck(paymentMethod));
    }

    @Test
    void isNotACheck() {
        PaymentMethod paymentMethod = PaymentMethod.createCreditCard(null,
                "12341234-1234-1234",
                "123",
                DateHelper.fromString("2025-01", true));
        assertFalse(CheckVerification.isACheck(paymentMethod));
    }

    @Test
    void isCashed() {
        PaymentMethod paymentMethod = PaymentMethod.createCheck(null, 123456789, true);
        assertTrue(CheckVerification.isCashed(paymentMethod));
    }

    @Test
    void isNotCashed() {
        PaymentMethod paymentMethod = PaymentMethod.createCheck(null, 123456789, false);
        assertFalse(CheckVerification.isCashed(paymentMethod));
    }
}
