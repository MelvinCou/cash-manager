package com.cashmanager.server.account.service;

import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.database.entity.PaymentMethod;

import java.util.Optional;

public interface IPaymentMethodService {

    Optional<PaymentMethod> checkPaymentMethodViability(PaymentMethodDto paymentMethodDto, TransactionDto transactionDto);
}
