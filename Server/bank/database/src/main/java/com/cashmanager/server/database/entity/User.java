package com.cashmanager.server.database.entity;

import com.cashmanager.server.database.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    @Setter(AccessLevel.NONE)
    private String password;

    @Enumerated
    @Column(name = "role", nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    private Set<Account> accounts = new LinkedHashSet<>();

    protected User() {}

    public User(String username, String password, UserRole role) {
        this.username = username;
        setPassword(password);
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }
}
