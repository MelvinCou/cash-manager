package com.cashmanager.server.account.verification;

import com.cashmanager.server.common.utils.DateHelper;
import com.cashmanager.server.database.entities.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditCardVerificationTest {
    @Test
    void validCreditCardNumber() {
        PaymentMethod paymentMethod = PaymentMethod.createCreditCard(null,
                "1234-1234-1234-1234",
                "123",
                DateHelper.fromString("2025-01", true));
        assertTrue(CreditCardVerification.verifyCreditCardNumber(paymentMethod));
    }

    @Test
    void invalidCreditCardNumber() {
        PaymentMethod paymentMethod = PaymentMethod.createCreditCard(null,
                "12341234-1234-1234",
                "123",
                DateHelper.fromString("2025-01", true));
        assertFalse(CreditCardVerification.verifyCreditCardNumber(paymentMethod));

        paymentMethod.setCreditCardNumber("1234-1234-1234-123");
        assertFalse(CreditCardVerification.verifyCreditCardNumber(paymentMethod));

        paymentMethod.setCreditCardNumber("1234-1234-1234-123$");
        assertFalse(CreditCardVerification.verifyCreditCardNumber(paymentMethod));
    }

    @Test
    void validValidityDate() {
        PaymentMethod paymentMethod = PaymentMethod.createCreditCard(null,
                "12341234-1234-1234",
                "123",
                DateHelper.fromString("2024-02", true));
        assertTrue(CreditCardVerification.verifyValidityDate(paymentMethod));

        paymentMethod.setValidityDate(DateHelper.nowYearMonthOnly());
        assertTrue(CreditCardVerification.verifyValidityDate(paymentMethod));

        paymentMethod.setValidityDate(LocalDateTime.MAX);
        assertTrue(CreditCardVerification.verifyValidityDate(paymentMethod));
    }

    @Test
    void invalidValidityDate() {
        PaymentMethod paymentMethod = PaymentMethod.createCreditCard(null,
                "12341234-1234-1234",
                "123",
                DateHelper.fromString("2021-01", true));
        assertFalse(CreditCardVerification.verifyValidityDate(paymentMethod));

        paymentMethod.setValidityDate(LocalDateTime.MIN);
        assertFalse(CreditCardVerification.verifyValidityDate(paymentMethod));
    }

    @Test
    void validCvc() {
        PaymentMethod paymentMethod = PaymentMethod.createCreditCard(null,
                "12341234-1234-1234",
                "123",
                DateHelper.fromString("2025-01", true));
        assertTrue(CreditCardVerification.verifyCvc(paymentMethod, paymentMethod.getCvc()));
    }

    @Test
    void invalidCvc() {
        PaymentMethod paymentMethod = PaymentMethod.createCreditCard(null,
                "12341234-1234-1234",
                "123",
                DateHelper.fromString("2025-01", true));
        assertFalse(CreditCardVerification.verifyCvc(paymentMethod, "321"));
    }

    @Test
    void validCreditCard() {
        PaymentMethod paymentMethod = PaymentMethod.createCreditCard(null,
                "12341234-1234-1234",
                "123",
                DateHelper.fromString("2025-01", true));
        assertTrue(CreditCardVerification.isACreditCard(paymentMethod));
    }

    @Test
    void invalidCreditCard() {
        PaymentMethod paymentMethod = PaymentMethod.createCheck(null, 123456789, false);
        assertFalse(CreditCardVerification.isACreditCard(paymentMethod));
    }
}