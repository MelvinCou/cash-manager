package com.cashmanager.server.account.service;

import com.cashmanager.server.account.verification.AccountVerification;
import com.cashmanager.server.common.dto.AccountDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.entity.Account;
import com.cashmanager.server.database.entity.AccountsLogs;
import com.cashmanager.server.database.mapper.AccountMapper;
import com.cashmanager.server.database.repository.AccountRepository;
import com.cashmanager.server.database.repository.AccountsLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class in charge of check detail from Account model
 */
@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final AccountsLogsRepository accountsLogsRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          AccountsLogsRepository accountsLogsRepository) {
        this.accountRepository = accountRepository;
        this.accountsLogsRepository = accountsLogsRepository;
    }

    /**
     * Used to check if account is ACTIVE.
     * Create log otherwise
     * @param accountId of user which want to pay
     * @return boolean
     */
    @Override
    public boolean checkAccountValidity(UUID accountId, TransactionDto transactionDto) {

        Optional<Account> account = accountRepository.findById(accountId);
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

    /**
     * Public class in charge of find the wanted account and verify if is active or no.
     * Make transaction log for each case.
     * @param accountId         - accountId of the wanted user
     * @param transactionDto    - Current transaction
     * @return                  - AccountDto object
     */
    public AccountDto checkAccount(UUID accountId, TransactionDto transactionDto) {

        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isPresent()) {
            if (AccountVerification.isActive(account)) {
                // Case active
                transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);
                accountsLogsRepository.save(new AccountsLogs(
                        null,
                        account.get(),
                        account.get().getUser().getUsername() + " account found",
                        LocalDateTime.now()));
            } else {
                // Case inactive
                transactionDto.setTransactionStatus(TransactionStatus.INACTIVE_ACCOUNT);
                accountsLogsRepository.save(new AccountsLogs(
                        null,
                        account.get(),
                        account.get().getUser().getUsername() + " account isn't active",
                        LocalDateTime.now()));
            }
        } else {
            // Case not found
            transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
            accountsLogsRepository.save(new AccountsLogs(
                    null,
                    account.get(),
                    accountId + " account not found",
                    LocalDateTime.now()));
        }

        return AccountMapper.INSTANCE.accountToAccountDto(account);
    }

    /**
     * Function used to transfer amount between client and store accounts, across AccountDto objects
     * @param storeAccount      - AccountDto object representing the store's account
     * @param clientAccount     - AccountDto object representing the client's account
     * @param transactionDto    - TransactionDto object representing the current transaction
     * @return                  - boolean, in charge to say if the money transaction between account went well.
     */
    public boolean transferAmountBetweenAccountsDto(AccountDto storeAccount, AccountDto clientAccount, TransactionDto transactionDto) {

        BigDecimal amount = transactionDto.getAmount();                 // Get amount of transaction
        BigDecimal oldClientBalance = clientAccount.getBalance();       // Get client balance before transaction
        BigDecimal oldStoreBalance = storeAccount.getBalance();         // Get store balance before transaction

        clientAccount.setBalance(oldClientBalance.subtract(amount));    // Subtract amount of transaction from the client balance

        BigDecimal newClientBalance = clientAccount.getBalance();       // Get the updated client balance

        storeAccount.setBalance(oldStoreBalance.add(amount));           // Add amount of transaction to the store account

        BigDecimal newStoreBalance = storeAccount.getBalance();         //  Get the updated store balance

        return (oldClientBalance.compareTo(newClientBalance) < 0) && (oldStoreBalance.compareTo(newStoreBalance) < 0);
    }

    /**
     * Function used to update balance account between store and client. In charge to communicate with the database.
     * @param storeAccountDto   - AccountDto object representing the store's account
     * @param clientAccountDto  - AccountDto object representing the client's account
     * @param transactionDto    - TransactionDto object representing the current transaction
     * @return                  - boolean in charge to describe state of update accounts balances
     */
    public boolean updateAccountBalance(AccountDto storeAccountDto, AccountDto clientAccountDto, TransactionDto transactionDto) {

        Optional<Account> storeAccount = accountRepository.findById(storeAccountDto.getId());
        Optional<Account> clientAccount = accountRepository.findById(clientAccountDto.getId());

        if (storeAccount.isEmpty() && clientAccount.isEmpty()) {
            transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
            accountsLogsRepository.save(new AccountsLogs(null, null, "store account and client account are not found", LocalDateTime.now()));
        } else if (storeAccount.isEmpty()) {
            transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
            accountsLogsRepository.save(new AccountsLogs(null, null, "store account is not found", LocalDateTime.now()));
        } else if (clientAccount.isEmpty()) {
            transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
            accountsLogsRepository.save(new AccountsLogs(null, null, "client account is not found", LocalDateTime.now()));
        } else {

            storeAccount.get().setBalance(storeAccountDto.getBalance());

            Account updatedStoreAccount = accountRepository.save(storeAccount.get());
            if (updatedStoreAccount != storeAccount.get()) {    // Store account Update went well
                transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);
                accountsLogsRepository.save(new AccountsLogs(null, updatedStoreAccount, "store account balance is updated", LocalDateTime.now()));
            } else {                                            // Store account update did not work
                transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
                accountsLogsRepository.save(new AccountsLogs(null, updatedStoreAccount, "store account balance has not updated", LocalDateTime.now()));

                return false;
            }

            clientAccount.get().setBalance(clientAccountDto.getBalance());

            Account updatedClientAccount = accountRepository.save(clientAccount.get());
            if (updatedClientAccount != clientAccount.get()) {   // Client account Update went well
                transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);
                accountsLogsRepository.save(new AccountsLogs(null, updatedClientAccount, "client account balance is updated", LocalDateTime.now()));

                return true;                                    // --- EVERYTHING OK ---
            } else {                                            // Client account update did not work
                transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
                accountsLogsRepository.save(new AccountsLogs(null, clientAccount.get(), "client account balance has not updated", LocalDateTime.now()));

                while (revertBalanceStoreAccount(storeAccount)) {
                    transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
                    accountsLogsRepository.save(new AccountsLogs(null, storeAccount.get(), "store account balance not yet revert", LocalDateTime.now()));
                }
            }
        }

        return false;
    }

    /**
     * Private function used to revert the balance of store account,
     * In case of he is already changed and one of the next step did not work.
     * E.g -> Updating the client account balance did not work
     * @param storeAccount  - Old state of store account before his updated
     * @return              - boolean in charge to describe if revert of account balance did work
     */
    private boolean revertBalanceStoreAccount(Optional<Account> storeAccount) {

        if (storeAccount.isPresent()) {
            Account updatedStoreAccount = accountRepository.save(storeAccount.get());
            return storeAccount.get() != updatedStoreAccount;
        }
        return true;
    }
}
