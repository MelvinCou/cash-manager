package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.DatabaseApplication;
import com.cashmanager.server.database.entities.Account;
import com.cashmanager.server.database.entities.PaymentMethod;
import com.cashmanager.server.database.entities.User;
import com.cashmanager.server.database.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseApplication.class)
class PaymentMethodRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createCreditCard() {
        // create user
        String username = "username" + ThreadLocalRandom.current().nextInt(0, 1000);
        String password = "password";
        UserRole role = UserRole.CLIENT;
        User user = userRepository.save(
                new User(username, password, role));
        // create account for user
        Account account = accountRepository.save(new Account(user));
        // create credit card for account
        String creditCardNumber = ThreadLocalRandom.current().nextInt(0, 9999)
                + "-5678-1234-" + ThreadLocalRandom.current().nextInt(0, 9999);
        String cvc = "123";
        LocalDateTime validityDate = LocalDateTime.now().plusYears(1);
        PaymentMethod paymentMethod = paymentMethodRepository.save(
                PaymentMethod.createCreditCard(account, creditCardNumber, cvc, validityDate));

        assertEquals(paymentMethod.getAccount().getId(), account.getId());
    }
}