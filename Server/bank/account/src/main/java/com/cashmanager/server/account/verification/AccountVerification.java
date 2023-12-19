package com.cashmanager.server.account.verification;

import com.cashmanager.server.database.entity.Account;
import com.cashmanager.server.database.enumeration.AccountState;

import java.math.BigDecimal;

public final class AccountVerification {
    /**
     * Private constructor to hide the implicit public one
     */
    private AccountVerification() {
    }

    /**
     * Verify if the account has enough money to make the transaction
     * @param account the account to verify
     * @param amount the amount of the transaction
     * @return true if the account has enough balance, false otherwise
     */
    public static boolean verifyBalance(Account account, BigDecimal amount) {
        /*
         * compareTo returns 1 if the BigDecimal is less than the argument
         */
        return account.getBalance().compareTo(amount) >= 0;
    }

    /**
     * Verify if the account is active
     * @param account the account to verify
     * @return true if the account is active, false otherwise
     */
    public static boolean isActive(Account account) {
        return account.getState().equals(AccountState.ACTIVE);
    }
}
