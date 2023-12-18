package com.cashmanager.server.mediator.service.interfaces;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.enumeration.OperationStep;
import com.cashmanager.server.common.enumeration.OrderStatus;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.mediator.utils.MediatorResponse;

import java.math.BigDecimal;
import java.util.UUID;


public interface IMediatorService {

    MediatorResponse notify (OperationStep operationStep, CartDto cart);
    MediatorResponse notify(OperationStep operationStep, OrderStatus orderStatus, UUID orderId);
    MediatorResponse notify (OperationStep operationStep, PaymentMethodDto paymentMethod, BigDecimal amount, String receiver);
    MediatorResponse notify(OperationStep operationStep, TransactionStatus transactionStatus, UUID transactionId);

}
