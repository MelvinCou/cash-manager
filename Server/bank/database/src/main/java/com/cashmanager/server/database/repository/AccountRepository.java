package com.cashmanager.server.database.repository;

import com.cashmanager.server.database.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    // get all accounts from a user
    Iterable<Account> findAllByUser_Id(UUID id);
}