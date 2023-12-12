package com.cashmanager.server.database.entities;

import com.cashmanager.server.database.enums.LogSeverity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "transaction_logs")
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    @ToString.Exclude
    private Transaction transaction;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated
    @Column(name = "severity", nullable = false)
    private LogSeverity severity;

    @Column(name = "message", nullable = false)
    private String message;

    public static TransactionLog error(Transaction transaction, String message) {
        return new TransactionLog(
                null,
                transaction,
                LocalDateTime.now(),
                LogSeverity.ERROR,
                message);
    }

    public static TransactionLog info(Transaction transaction, String message) {
        return new TransactionLog(
                null,
                transaction,
                LocalDateTime.now(),
                LogSeverity.INFO,
                message);
    }

    public static TransactionLog warn(Transaction transaction, String message) {
        return new TransactionLog(
                null,
                transaction,
                LocalDateTime.now(),
                LogSeverity.WARN,
                message);
    }
}