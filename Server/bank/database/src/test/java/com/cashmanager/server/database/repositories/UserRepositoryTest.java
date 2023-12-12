package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.DatabaseApplication;
import com.cashmanager.server.database.entities.User;
import com.cashmanager.server.database.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseApplication.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUser() {
        String username = "username" + ThreadLocalRandom.current().nextInt(0, 1000);
        String password = "password";
        UserRole role = UserRole.CLIENT;
        User user = userRepository.save(
                new User(username, password, role));
        User userFromDb = userRepository.findByUsername(username).get();

        assertEquals(user.getId(), userFromDb.getId());
        assertEquals(username, userFromDb.getUsername());
        assertTrue(userFromDb.checkPassword(password));
        assertEquals(role, userFromDb.getRole());
    }
}