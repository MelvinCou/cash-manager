package com.cashmanager.server.account.repositories;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.UUID;

/**
 * Available roles for a user
 */
enum Role {
    CLIENT,
    ADMIN
}

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String username;
    private String password;
    private Role role;

    protected User() {}

    public User(String username, String password, Role role) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', password='%s', role='%s']",
                id, username, password, role);
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}