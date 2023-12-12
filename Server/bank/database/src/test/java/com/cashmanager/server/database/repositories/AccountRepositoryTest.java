package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.DatabaseApplication;
import com.cashmanager.server.database.entities.Account;
import com.cashmanager.server.database.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseApplication.class)
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void insertAccount() {
        // create user
        User user = userRepository.save(Helpers.createUser());
        // create account for user
        Account account = accountRepository.save(new Account(user));

        assertEquals(account.getId(), accountRepository.findAllByUser_Id(user.getId()).iterator().next().getId());
        assertEquals(account.getUser().getId(), user.getId());
    }
}