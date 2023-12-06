package com.cashmanager.server.database.repositories.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountsLogsRepository extends JpaRepository<AccountsLogs, UUID> {
}