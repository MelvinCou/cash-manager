package com.cashmanager.server.database.repository;

import com.cashmanager.server.database.entity.TransactionLog;
import com.cashmanager.server.database.enumeration.LogSeverity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, UUID> {
    // find by severity
    Iterable<TransactionLog> findAllBySeverity(LogSeverity severity);

    // get all logs from transaction
    Iterable<TransactionLog> findAllByTransaction_Id(UUID id);

}