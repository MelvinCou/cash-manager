package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.entities.AccountsLogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountsLogsRepository extends JpaRepository<AccountsLogs, UUID> {
    // get all logs from an account
    Iterable<AccountsLogs> findAllByAccount_Id(UUID id);
}