package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.DatabaseApplication;
import com.cashmanager.server.database.entities.*;
import com.cashmanager.server.database.enums.LogSeverity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseApplication.class)
class TransactionLogRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionLogRepository transactionLogRepository;
    @Autowired
    private UserRepository userRepository;

    private Transaction transaction;

    @Test
    public void insertError() {
        User user = userRepository.save(Helpers.createUser());
        Account account = accountRepository.save(new Account(user));
        PaymentMethod paymentMethod = paymentMethodRepository.save(
                PaymentMethod.createCheck(account, 1, false));
        transaction = transactionRepository.save(
                Helpers.createTransaction(paymentMethod));

        String error = "Something went wrong";
        TransactionLog log = transactionLogRepository.save(TransactionLog.error(transaction, error));

        assertEquals(error, log.getMessage());
        assertEquals(transaction.getId(), log.getTransaction().getId());
        assertEquals(LogSeverity.ERROR, log.getSeverity());
    }

    @Test
    public void insertInfo() {
        User user = userRepository.save(Helpers.createUser());
        Account account = accountRepository.save(new Account(user));
        PaymentMethod paymentMethod = paymentMethodRepository.save(
                PaymentMethod.createCheck(account, 1, false));
        transaction = transactionRepository.save(
                Helpers.createTransaction(paymentMethod));

        String info = "Something has been done";
        TransactionLog log = transactionLogRepository.save(TransactionLog.info(transaction, info));

        assertEquals(info, log.getMessage());
        assertEquals(transaction.getId(), log.getTransaction().getId());
        assertEquals(LogSeverity.INFO, log.getSeverity());
    }

    @Test
    public void insertWarn() {
        User user = userRepository.save(Helpers.createUser());
        Account account = accountRepository.save(new Account(user));
        PaymentMethod paymentMethod = paymentMethodRepository.save(
                PaymentMethod.createCheck(account, 1, false));
        transaction = transactionRepository.save(
                Helpers.createTransaction(paymentMethod));

        String warning = "Something not expected happened";
        TransactionLog log = transactionLogRepository.save(TransactionLog.warn(transaction, warning));

        assertEquals(warning, log.getMessage());
        assertEquals(transaction.getId(), log.getTransaction().getId());
        assertEquals(LogSeverity.WARN, log.getSeverity());
    }
}