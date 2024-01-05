package com.cashmanager.server.transaction.controller;

import com.cashmanager.server.account.service.AccountService;
import com.cashmanager.server.account.service.PaymentMethodService;
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
import com.cashmanager.server.transaction.model.TransactionHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

                saveTransaction(paymentMethodDto, transactionDto, transactionHolder);
                saveTransactionLog(transactionDto, "payment method and account validation ok");
            } else {
                transactionDto.setTransactionStatus(TransactionStatus.CANCELED);

                saveTransaction(paymentMethodDto, transactionDto, transactionHolder);
                saveTransactionLog(transactionDto, "account validation nok");
            }
        } else {
            transactionDto.setTransactionStatus(TransactionStatus.CANCELED);

            saveTransaction(paymentMethodDto, transactionDto, transactionHolder);
            saveTransactionLog(transactionDto, "payment method nok");
        }
    }

    /**
     * Private method used to save the current transaction
     * @param paymentMethodDto  -
     * @param transactionDto    -
     * @param transactionHolder - Object containing payment method and amount of transaction
     */
    private void saveTransaction(PaymentMethodDto paymentMethodDto, TransactionDto transactionDto, TransactionHolder transactionHolder) {

        transactionRepository.save(new Transaction(
                null,
                PaymentMethodMapper.INSTANCE.paymentMethodDtoToPaymentMethod(paymentMethodDto),
                transactionDto.getTransactionStatus(),
                transactionHolder.getAmount(),
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
                LogSeverity.INFO, "payment method and account validation ok"));
    }
}
