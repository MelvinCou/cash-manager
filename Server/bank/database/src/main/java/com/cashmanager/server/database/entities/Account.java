package com.cashmanager.server.database.entities;

import com.cashmanager.server.database.enums.EnumAccountState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "accounts")
public class Account {
    @Id
    private UUID id;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "opening_date", nullable = false)
    private LocalDateTime openingDate;

    @Enumerated
    @Column(name = "state", nullable = false)
    private EnumAccountState state;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private Set<PaymentMethod> paymentMethods = new LinkedHashSet<>();

}
