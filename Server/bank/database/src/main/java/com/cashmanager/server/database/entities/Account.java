package com.cashmanager.server.database.entities;

import com.cashmanager.server.database.enums.AccountState;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @Column(name = "opening_date", nullable = false)
    private LocalDateTime openingDate;

    @Enumerated
    @Column(name = "state", nullable = false)
    private AccountState state;

    @Column(name = "balance", nullable = false, precision = 19, scale = 4)
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    @ToString.Exclude
    private Set<PaymentMethod> paymentMethods = new LinkedHashSet<>();

    public Account(User user) {
        this.user = user;
        this.openingDate = LocalDateTime.now();
        this.state = AccountState.ACTIVE;
        this.balance = BigDecimal.ZERO;
    }
}
