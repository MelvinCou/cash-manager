package com.cashmanager.server.account.repositories;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "opening_date", nullable = false)
    private LocalDateTime openingDate;

    @Enumerated
    @Column(name = "state", nullable = false)
    private AccountState state;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;
}
