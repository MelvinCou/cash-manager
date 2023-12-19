package com.cashmanager.server.account.controller;

import com.cashmanager.server.account.verification.AccountVerification;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.database.entity.Account;
import org.springframework.stereotype.Controller;

/**
 * Controller in charge of check detail about Account model
 */
@Controller
public class AccountController {

    /**
     * Used to check if account is ACTIVE.
     * Create log otherwise
     * @param account of user which want to pay
     * @return boolean
     */
    public boolean checkAccountValidity(Account account, TransactionDto transactionDto) {

        boolean active = AccountVerification.isActive(account);
        boolean sufficientBalance = AccountVerification.verifyBalance(account, transactionDto.getAmount());

        if (!active && !sufficientBalance) {
            // TODO -> Create log, case of Account is INACTIVE and balance is insufficient
            return false;
        } else if (!active) {
            // TODO -> Create log, case of Account is INACTIVE
            return false;
        } else if (!sufficientBalance) {
            // TODO -> Create log, case of balance is insufficient
            return false;
        }
        return true;
    }
}
