package com.cashmanager.server.database.repositories.user;

import com.cashmanager.server.database.DatabaseApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseApplication.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUser() {
        String username = "username";
        String password = "password";
        EnumUserRole role = EnumUserRole.CLIENT;
        User user = userRepository.save(
                new User(username, password, role));
        User userFromDb = userRepository.findById(user.getId()).get();

        assertEquals(user.getId(), userFromDb.getId());
        assertEquals(username, userFromDb.getUsername());
        assertTrue(userFromDb.checkPassword(password));
        assertEquals(role, userFromDb.getRole());
    }
}