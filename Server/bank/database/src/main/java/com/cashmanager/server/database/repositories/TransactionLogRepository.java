package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.entities.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, UUID> {
}