package com.cashmanager.server.database.entities;

import com.cashmanager.server.database.enums.LogSeverity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "transaction_logs")
public class TransactionLog {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated
    @Column(name = "severity", nullable = false)
    private LogSeverity severity;

    @Column(name = "message", nullable = false)
    private String message;
}