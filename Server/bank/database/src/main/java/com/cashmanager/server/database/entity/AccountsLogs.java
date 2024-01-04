package com.cashmanager.server.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "accounts_logs")
public class AccountsLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @ToString.Exclude
    private Account account;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    /**
     * Constructor used to create error log
     * @param account of user
     * @param message error
     * @return AccountsLogs object
     */
    public static AccountsLogs error(Account account, String message) {
        return new AccountsLogs(
                null,
                account,
                message,
                LocalDateTime.now()
        );
    }

    /**
     * Constructor used to create warn log
     * @param account of user
     * @param message warn
     * @return AccountsLogs object
     */
    public static AccountsLogs warn(Account account, String message) {
        return new AccountsLogs(
                null,
                account,
                message,
                LocalDateTime.now()
        );
    }

    /**
     * Constructor used to create info log
     * @param account of user
     * @param message info
     * @return AccountsLogs object
     */
    public static AccountsLogs info(Account account, String message) {
        return new AccountsLogs(
                null,
                account,
                message,
                LocalDateTime.now()
        );
    }
}
