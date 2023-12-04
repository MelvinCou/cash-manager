package com.cashmanager.server.account.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void createUser() {
        String username = "username";
        String password = "password";
        Role role = Role.CLIENT;
        User user = new User(username, password, role);

        assertEquals(user.getUsername(), username);
        assertTrue(BCrypt.checkpw(password, user.getPassword()));
        assertEquals(user.getRole(), role);
    }
}
