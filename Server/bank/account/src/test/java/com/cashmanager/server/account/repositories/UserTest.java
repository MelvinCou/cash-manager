package com.cashmanager.server.account.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void createUser() {
        String username = "username";
        String password = "password";
        UserRole role = UserRole.CLIENT;
        User user = new User(username, password, role);

        assertFalse(user.getId().toString().isEmpty());
        assertEquals(username, user.getUsername());
        assertTrue(BCrypt.checkpw(password, user.getPassword()));
        assertEquals(role, user.getRole());
    }
}
