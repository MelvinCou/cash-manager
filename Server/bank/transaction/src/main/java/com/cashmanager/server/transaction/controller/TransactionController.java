package com.cashmanager.server.transaction.controller;

import com.cashmanager.server.account.service.AccountService;
import com.cashmanager.server.account.service.PaymentMethodService;
import com.cashmanager.server.common.dto.AccountDto;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.entity.PaymentMethod;
import com.cashmanager.server.database.entity.Transaction;
import com.cashmanager.server.database.entity.TransactionLog;
import com.cashmanager.server.database.enumeration.LogSeverity;
import com.cashmanager.server.database.mapper.PaymentMethodMapper;
import com.cashmanager.server.database.mapper.TransactionMapper;
import com.cashmanager.server.database.repository.AccountRepository;
import com.cashmanager.server.database.repository.TransactionLogRepository;
import com.cashmanager.server.database.repository.TransactionRepository;
import com.cashmanager.server.transaction.model.AccountTransactionHolder;
import com.cashmanager.server.transaction.model.TransactionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Controller in charge of dispatching request for returning the validation of a bank transaction
 */
@RestController
public class TransactionController {

    private TransactionLogRepository transactionLogRepository;
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private PaymentMethodService paymentMethodService;
    private AccountService accountService;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository, PaymentMethodService paymentMethodService) {
        this.transactionRepository = transactionRepository;
        this.paymentMethodService = paymentMethodService;
    }

    /**
     * Starting point from mediator for payment method and transaction steps
     * @param transactionHolder - Object containing payment method and amount of transaction
     */
    @PostMapping("/transaction")
    public void checkTransaction(@RequestBody TransactionHolder transactionHolder) {

        PaymentMethodDto paymentMethodDto = transactionHolder.getPaymentMethodDto();
        TransactionDto transactionDto = TransactionHolder.setupTransactionDto(transactionHolder);

        Optional<PaymentMethod> paymentMethod = paymentMethodService.checkPaymentMethodViability(paymentMethodDto, transactionDto);
        if (paymentMethod.isPresent()) {
            if (accountService.checkAccountValidity(paymentMethod.get().getAccount().getId(), transactionDto)) {
                transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_ACCEPTED);

                saveTransaction(paymentMethodDto, transactionDto, transactionHolder.getAmount());
                saveTransactionLog(transactionDto, "payment method and account validation ok");
            } else {
                transactionDto.setTransactionStatus(TransactionStatus.CANCELED);

                saveTransaction(paymentMethodDto, transactionDto, transactionHolder.getAmount());
                saveTransactionLog(transactionDto, "account validation nok");
            }
        } else {
            transactionDto.setTransactionStatus(TransactionStatus.CANCELED);

            saveTransaction(paymentMethodDto, transactionDto, transactionHolder.getAmount());
            saveTransactionLog(transactionDto, "payment method nok");
        }
    }

    /**
     * Starting point from mediator for accounts balance transaction steps
     * @param accountTransactionHolder  - see his documentation
     * @return                          - boolean in charge to describe state of transaction step
     */
    @PostMapping("/account_transaction")
    public boolean checkAccountTransaction(@RequestBody AccountTransactionHolder accountTransactionHolder) {

        TransactionDto transactionDto = AccountTransactionHolder.setupTransactionDto(accountTransactionHolder);
        AccountDto storeAccount = accountService.checkAccount(accountTransactionHolder.getStoreAccountId(), transactionDto);
        AccountDto clientAccount = accountService.checkAccount(accountTransactionHolder.getClientAccountId(), transactionDto);

        if (storeAccount != null && clientAccount != null) {
            if (storeAccount.getId() != clientAccount.getId()) {
                // Start the balance transaction between accounts
                if (accountService.transferAmountBetweenAccountsDto(storeAccount, clientAccount, transactionDto)) {
                    transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);
                    if (accountService.updateAccountBalance(storeAccount, clientAccount, transactionDto)) {
                        transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_ACCEPTED);
                        saveTransactionLog(transactionDto, "accounts updated, payment accepted");
                        return true;
                    } else { // Update of accounts entities did not work
                        transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
                        saveTransactionLog(transactionDto, "update of accounts balance did not work");
                    }
                } else { // Transfer between accounts DTO failed
                    transactionDto.setTransactionStatus(TransactionStatus.CANCELED);
                    saveTransactionLog(transactionDto, "transfer between accounts dto failed");
                }
            } else { // Case store and client accounts are same, cannot valid
                transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
                saveTransactionLog(transactionDto, "accounts are same");
            }
        } else { // Case one or both accounts not found
            transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
            saveTransactionLog(transactionDto, "one or both accounts not found");
        }
        return false;
    }

    /**
     * Private method used to save the current transaction
     * @param paymentMethodDto  -
     * @param transactionDto    -
     * @param amount - Amount of transaction
     */
    private void saveTransaction(PaymentMethodDto paymentMethodDto, TransactionDto transactionDto, BigDecimal amount) {

        transactionRepository.save(new Transaction(
                null,
                PaymentMethodMapper.INSTANCE.paymentMethodDtoToPaymentMethod(paymentMethodDto),
                transactionDto.getTransactionStatus(),
                amount,
                LocalDateTime.now(),
                ""));
    }

    /**
     * Private method used to save the transaction log
     * @param transactionDto    -
     * @param message           - String indicate the reason of log creation
     */
    private void saveTransactionLog(TransactionDto transactionDto, String message) {

        transactionLogRepository.save(new TransactionLog(
                null,
                TransactionMapper.INSTANCE.transactionDtoToTransaction(transactionDto),
                LocalDateTime.now(),
                LogSeverity.INFO,
                message));
    }
}
