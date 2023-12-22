package com.cashmanager.server.account.service;

import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.database.entity.Account;

public interface IAccountService {

    boolean checkAccountValidity(Account account, TransactionDto transactionDto);
}
