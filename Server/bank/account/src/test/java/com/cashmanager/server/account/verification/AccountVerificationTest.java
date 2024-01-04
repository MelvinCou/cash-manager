package com.cashmanager.server.account.verification;

import com.cashmanager.server.database.entity.Account;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountVerificationTest {

    @Test
    void validBalance() {
        BigDecimal amount = BigDecimal.valueOf(100.85);
        Account account = new Account(null, null, null, null,
                BigDecimal.valueOf(1000.225), null);
        assertTrue(AccountVerification.verifyBalance(Optional.of(account), amount));

        account.setBalance(BigDecimal.valueOf(520.74));
        assertTrue(AccountVerification.verifyBalance(Optional.of(account), amount));

        account.setBalance(BigDecimal.valueOf(100.85));
        assertTrue(AccountVerification.verifyBalance(Optional.of(account), amount));
    }

    @Test
    void invalidBalance() {
        BigDecimal amount = BigDecimal.valueOf(100.85);
        Account account = new Account(null);
        assertFalse(AccountVerification.verifyBalance(Optional.of(account), amount));

        account.setBalance(BigDecimal.valueOf(100.84999999999));
        assertFalse(AccountVerification.verifyBalance(Optional.of(account), amount));
    }

    @Test
    void isActive() {
        Account account = new Account(null);
        assertTrue(AccountVerification.isActive(Optional.of(account)));
    }
}
