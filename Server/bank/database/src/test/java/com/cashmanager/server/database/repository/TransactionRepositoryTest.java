package com.cashmanager.server.database.repository;

import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.DatabaseApplication;
import com.cashmanager.server.database.entity.Account;
import com.cashmanager.server.database.entity.PaymentMethod;
import com.cashmanager.server.database.entity.Transaction;
import com.cashmanager.server.database.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseApplication.class)
class TransactionRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void insertTransaction() {
        // create user
        User user = userRepository.save(Helpers.createUser());
        // create account for user
        Account account = accountRepository.save(new Account(user));
        // create credit card for account
        String creditCardNumber = Helpers.getRandomInt(1000, 9999)
                + "-5678-1234-" + Helpers.getRandomInt(1000, 9999);
        String cvc = "123";
        LocalDateTime validityDate = LocalDateTime.now().plusYears(1);
        PaymentMethod paymentMethod = paymentMethodRepository.save(
                PaymentMethod.createCreditCard(account, creditCardNumber, cvc, validityDate));
        // create transaction
        Transaction transaction = transactionRepository.save(
                new Transaction(paymentMethod, TransactionStatus.PAYMENT_IN_PROGRESS, new BigDecimal(10), "test"));

        assertEquals(transaction.getPaymentMethod().getId(), paymentMethod.getId());
    }
}