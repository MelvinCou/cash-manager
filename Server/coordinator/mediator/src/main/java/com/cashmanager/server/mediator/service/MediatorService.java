package com.cashmanager.server.mediator.service;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.OrderDto;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.OperationStatus;
import com.cashmanager.server.common.enumeration.OperationStep;
import com.cashmanager.server.common.enumeration.OrderStatus;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.common.utils.Messages;
import com.cashmanager.server.mediator.service.interfaces.IMediatorService;
import com.cashmanager.server.mediator.utils.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class MediatorService implements IMediatorService {

    private final ShopTransmitter shopTransmitter;
    private final BankTransmitter bankTransmitter;

    public MediatorService(ShopTransmitter shopTransmitter, BankTransmitter bankTransmitter) {
        this.shopTransmitter = shopTransmitter;
        this.bankTransmitter = bankTransmitter;
    }

    @Override
    public MediatorResponse notify(OperationStep operationStep, CartDto cart) {
        String message = "";
        //Step : send the cart info to the shop service
        TransmitterResponse<OrderDto> response = shopTransmitter.createOrder(cart);
        if(response.getOperationStep().equals(OperationStep.CREATE_ORDER)
                && response.getOperationStatus().equals(OperationStatus.SUCCESS)
                && response.getData()!= null){
            message = Messages.format("order_created", response.getData().getId());
        }
        //if one of the products are unavailable
        else if(response.getOperationStatus().equals(OperationStatus.ABORTED)
                && response.getOperationStep().equals(OperationStep.CHECK_INVENTORY)){
            message = Messages.getString("insufficient_stock");
        }
        //if an error occurred during the creation of the order TODO à détailler
        else if(response.getOperationStatus().equals(OperationStatus.ERROR)
                && response.getOperationStep().equals(OperationStep.CREATE_ORDER)){
            message = Messages.getString("order_creation_error");
        }
        return new MediatorResponse(response.getOperationStep(),
                response.getOperationStatus(), message,
                response.getData()!= null ? response.getData().getId() : null);
    }

    @Override
    public MediatorResponse notify(OperationStep operationStep, OrderStatus orderStatus, UUID orderId) {
        String message = "";
        //if the order is canceled, reverse the inventory
        if(orderStatus.equals(OrderStatus.CANCELED) || orderStatus.equals(OrderStatus.PAYMENT_REJECTED)){
            TransmitterResponse<String> reverseResponse = shopTransmitter.reverseInventory(orderId);
            if(reverseResponse.getOperationStatus().equals(OperationStatus.ERROR)){
                return new MediatorResponse(OperationStep.UPDATE_INVENTORY,
                        OperationStatus.ERROR,
                        Messages.format("reverse_inventory_error",
                        orderId));
            }
        }
        //Step : update the order (when payment process is done (success or not))
        TransmitterResponse<String> response = shopTransmitter.updateOrder(orderId, orderStatus);
        if(response.getOperationStep().equals(OperationStep.UPDATE_ORDER)
                && response.getOperationStatus().equals(OperationStatus.SUCCESS) ){
            message = Messages.format("order_update", orderId);
        }
        else{
            //if an error occurred during the update of the order TODO à détailler
            message = Messages.format("order_update_error", orderId);
        }
        return new MediatorResponse(response.getOperationStep(),
                response.getOperationStatus(),message);
    }

    @Override
    public MediatorResponse notify(OperationStep operationStep, PaymentMethodDto paymentMethod, BigDecimal amount, String receiver) {
        String message ="";
        //Step : send the payment method and the amount
        TransmitterResponse<TransactionDto> response = bankTransmitter.createTransaction(paymentMethod,amount, receiver);
        if(response.getOperationStep().equals(OperationStep.PAYMENT_DONE)
                && response.getOperationStatus().equals(OperationStatus.SUCCESS)
                && response.getData() != null){
            message = Messages.format("payment_done",response.getData().getId());
        }
        else{
            //if payment invalid, non-active account , or balance insufficient
            message = response.getMessage(); // the message response contained the transactionStatus
        }
        return new MediatorResponse(response.getOperationStep(),
                response.getOperationStatus(),message,
                response.getData() != null ? response.getData().getId() : null);
    }

    @Override
    public MediatorResponse notify(OperationStep operationStep, TransactionStatus transactionStatus, UUID transactionId) {
        String message = "";
        //if payment is canceled after the transfert is done
        if(transactionStatus.equals(TransactionStatus.CANCELED) ){
            TransmitterResponse<String> reverseResponse = bankTransmitter.reversePayment(transactionId);
            if(reverseResponse.getOperationStatus().equals(OperationStatus.ERROR)){
                return new MediatorResponse(OperationStep.UPDATE_TRANSACTION,
                        OperationStatus.ERROR, Messages.format("reverse_payment_error",
                        transactionId));
            }
        }

        //Step : update the transaction (when payment process is done (success or not))
        TransmitterResponse<String> response = bankTransmitter.updateTransaction(transactionStatus, transactionId);
        if(response.getOperationStep().equals(OperationStep.UPDATE_TRANSACTION)
                && response.getOperationStatus().equals(OperationStatus.SUCCESS)){
            message = Messages.format("transaction_updated", transactionId);
        }
        else{
            message = response.getMessage();
        }
        return new MediatorResponse(response.getOperationStep(),
                response.getOperationStatus(),message,
                transactionId);
    }

}
