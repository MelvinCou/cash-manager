package com.cashmanager.server.account.service;

import com.cashmanager.server.account.verification.AccountVerification;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.entity.Account;
import com.cashmanager.server.database.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class in charge of check detail from Account model
 */
@Service
public class AccountService implements IAccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Used to check if account is ACTIVE.
     * Create log otherwise
     * @param account of user which want to pay
     * @return boolean
     */
    @Override
    public boolean checkAccountValidity(Account account, TransactionDto transactionDto) {

        boolean active = AccountVerification.isActive(account);
        boolean sufficientBalance = AccountVerification.verifyBalance(account, transactionDto.getAmount());

        if (!active && !sufficientBalance) {
            transactionDto.setTransactionStatus(TransactionStatus.INSUFFICIENT_BALANCE);
            transactionDto.setTransactionStatus(TransactionStatus.INACTIVE_ACCOUNT);
            // TODO -V- @HELP uncomment this line
//             accountRepository.save(new AccountsLogs(null, account, "", LocalDateTime.now()));
            // TODO -> Create log, case of Account is INACTIVE and balance is insufficient
            return false;
        } else if (!active) {
            transactionDto.setTransactionStatus(TransactionStatus.INACTIVE_ACCOUNT);
            // TODO -> Create log, case of Account is INACTIVE
            return false;
        } else if (!sufficientBalance) {
            transactionDto.setTransactionStatus(TransactionStatus.INSUFFICIENT_BALANCE);
            // TODO -> Create log, case of balance is insufficient
            return false;
        }
        transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);
        return true;
    }
}
