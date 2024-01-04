package com.cashmanager.server.account.service;

import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.database.entity.Account;

import java.util.Optional;

public interface IAccountService {

    boolean checkAccountValidity(Optional<Account> account, TransactionDto transactionDto);
}
