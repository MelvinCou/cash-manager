package com.cashmanager.server.account.service;

import com.cashmanager.server.common.dto.TransactionDto;

import java.util.UUID;

public interface IAccountService {

    boolean checkAccountValidity(UUID account, TransactionDto transactionDto);
}
