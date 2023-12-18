package com.cashmanager.server.mediator.utils;

import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.OperationStatus;
import com.cashmanager.server.common.enumeration.OperationStep;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class BankTransmitter {
    public TransmitterResponse<TransactionDto> createTransaction(PaymentMethodDto paymentMethod, BigDecimal amount, String receiver){
        //TODO à supprimer
        TransactionDto transaction = new TransactionDto(LocalDateTime.now().toString(),TransactionStatus.PAYMENT_IN_PROGRESS,paymentMethod,amount, receiver);
        TransmitterResponse<TransactionDto> response = new TransmitterResponse<>();
        response.setOperationStep(OperationStep.PAYMENT_DONE);
        response.setOperationStatus(OperationStatus.SUCCESS);
        response.setData(transaction);

        //TODO communication with BankController
        return response;
    }

    public TransmitterResponse<String>updateTransaction(TransactionStatus transactionStatus, UUID transactionId){
        //TODO à supprimer
        TransmitterResponse<String> response = new TransmitterResponse<>();
        response.setOperationStep(OperationStep.UPDATE_TRANSACTION);
        response.setOperationStatus(OperationStatus.SUCCESS);
        response.setMessage("PAYMENT_ACCEPTED");

        //TODO communication with BankController
        return response;
    }

    public TransmitterResponse<String> reversePayment(UUID transactionId){

        //TODO communication with BankController
        return null;
    }
}
