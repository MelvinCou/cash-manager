package com.cashmanager.server.account.service;

import com.cashmanager.server.account.verification.AccountVerification;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.entity.Account;
import com.cashmanager.server.database.entity.AccountsLogs;
import com.cashmanager.server.database.repository.AccountRepository;
import com.cashmanager.server.database.repository.AccountsLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class in charge of check detail from Account model
 */
@Service
public class AccountService implements IAccountService {

    private final AccountsLogsRepository accountsLogsRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          AccountsLogsRepository accountsLogsRepository) {
        this.accountsLogsRepository = accountsLogsRepository;
    }

    /**
     * Used to check if account is ACTIVE.
     * Create log otherwise
     * @param account of user which want to pay
     * @return boolean
     */
    @Override
    public boolean checkAccountValidity(Optional<Account> account, TransactionDto transactionDto) {

        boolean active = AccountVerification.isActive(account);
        boolean sufficientBalance = AccountVerification.verifyBalance(account, transactionDto.getAmount());

        if (!active && !sufficientBalance) {
            transactionDto.setTransactionStatus(TransactionStatus.INSUFFICIENT_BALANCE);
            transactionDto.setTransactionStatus(TransactionStatus.INACTIVE_ACCOUNT);
             accountsLogsRepository.save(new AccountsLogs(null, account.get(), "insufficient balance and inactive account", LocalDateTime.now()));

            return false;
        } else if (!active) {
            transactionDto.setTransactionStatus(TransactionStatus.INACTIVE_ACCOUNT);
            accountsLogsRepository.save(new AccountsLogs(null, account.get(), "inactive account", LocalDateTime.now()));

            return false;
        } else if (!sufficientBalance) {
            transactionDto.setTransactionStatus(TransactionStatus.INSUFFICIENT_BALANCE);
            accountsLogsRepository.save(new AccountsLogs(null, account.get(), "insufficient balance", LocalDateTime.now()));

            return false;
        }
        transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);
        return true;
    }
}
