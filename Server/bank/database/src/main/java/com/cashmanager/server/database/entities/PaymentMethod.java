package com.cashmanager.server.database.entities;

import com.cashmanager.server.database.enums.PaymentMethodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "payment_methods")
public class PaymentMethod {
    @Id
    private UUID id;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated
    @Column(name = "type", nullable = false)
    private PaymentMethodType type;

    @Column(name = "credit_card_number", unique = true, length = 19)
    private String creditCardNumber;

    @Column(name = "cvc", length = 3)
    private String cvc;

    @Column(name = "validity_date")
    private LocalDateTime validityDate;

    @Column(name = "check_number")
    private Integer checkNumber;
}
