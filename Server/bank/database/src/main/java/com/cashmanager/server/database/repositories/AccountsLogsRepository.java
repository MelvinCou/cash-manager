package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.entities.AccountsLogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountsLogsRepository extends JpaRepository<AccountsLogs, UUID> {
    Logger logger = LoggerFactory.getLogger(AccountsLogsRepository.class);
}