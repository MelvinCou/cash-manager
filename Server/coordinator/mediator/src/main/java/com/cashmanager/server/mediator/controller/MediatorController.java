package com.cashmanager.server.mediator.controller;

import com.cashmanager.server.common.enumeration.OperationStatus;
import com.cashmanager.server.common.enumeration.OperationStep;
import com.cashmanager.server.common.enumeration.OrderStatus;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.common.utils.Messages;
import com.cashmanager.server.mediator.utils.MediatorResponse;
import com.cashmanager.server.mediator.utils.ObjectHolder;
import com.cashmanager.server.mediator.service.interfaces.IMediatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MediatorController {

    private final IMediatorService mediatorService;

    public MediatorController(IMediatorService mediatorService) {
        this.mediatorService = mediatorService;
    }

    @PostMapping("/mediator")
    public ResponseEntity<String> newOrderAndPayment(@RequestBody ObjectHolder objectHolder){
        String message ="";
        UUID orderId = null;
        UUID transactionId = null;

        //TODO see how to refactor
        if(objectHolder != null && objectHolder.getCart() != null
                && objectHolder.getPaymentMethod() != null
                && objectHolder.getAmount() != null){
            //Send the cart to shop-service
        MediatorResponse mediatorResponse1 = mediatorService.notify(OperationStep.CREATE_ORDER,objectHolder.getCart());
            if(mediatorResponse1.getCurrentOperationStep().equals(OperationStep.CREATE_ORDER)
                    && mediatorResponse1.getOperationStatus().equals(OperationStatus.SUCCESS)){
                orderId = mediatorResponse1.getItemId();
                System.out.println("ID ORDER "+mediatorResponse1.getItemId());
                //Send the payment info and the amount of the order
                MediatorResponse mediatorResponse2 = mediatorService.notify(OperationStep.CREATE_TRANSACTION,objectHolder.getPaymentMethod(),objectHolder.getAmount(), objectHolder.getReceiver());
                transactionId = mediatorResponse2.getItemId();
                if(mediatorResponse2.getCurrentOperationStep().equals(OperationStep.PAYMENT_DONE)
                        && mediatorResponse2.getOperationStatus().equals(OperationStatus.SUCCESS)){
                    MediatorResponse mediatorResponse3 = mediatorService.notify(OperationStep.UPDATE_ORDER, OrderStatus.PAYMENT_ACCEPTED,orderId);
                    MediatorResponse mediatorResponse4 = mediatorService.notify(OperationStep.UPDATE_TRANSACTION, TransactionStatus.PAYMENT_ACCEPTED, transactionId);

                    if(!mediatorResponse3.getOperationStatus().equals(OperationStatus.SUCCESS)
                            && mediatorResponse4.getOperationStatus().equals(OperationStatus.SUCCESS)){
                        message = mediatorResponse3.getMessage();
                    }
                    else if (mediatorResponse3.getOperationStatus().equals(OperationStatus.SUCCESS)
                            && !mediatorResponse4.getOperationStatus().equals(OperationStatus.SUCCESS)){
                        message = mediatorResponse4.getMessage();
                    }
                    else{
                        message ="Payment accepted for the order number " + orderId;
                    }


                }else{
                    //if payment failed, we need to update the order and transaction (the reverse of the inventory or the payment is handle automatically
                    MediatorResponse mediatorResponse5 = mediatorService.notify(OperationStep.UPDATE_ORDER, OrderStatus.PAYMENT_REJECTED,orderId);
                    TransactionStatus transactionStatus = switch (mediatorResponse2.getMessage()) {
                        case "INSUFFICIENT_BALANCE" -> TransactionStatus.INSUFFICIENT_BALANCE;
                        case "INCORRECT_PAYMENT_INFO" -> TransactionStatus.INCORRECT_PAYMENT_INFO;
                        case "INACTIVE_ACCOUNT" -> TransactionStatus.INACTIVE_ACCOUNT;
                        default -> TransactionStatus.CANCELED;
                    };
                    MediatorResponse mediatorResponse6 = mediatorService.notify(OperationStep.UPDATE_TRANSACTION, transactionStatus, transactionId);

                    if(!mediatorResponse5.getOperationStatus().equals(OperationStatus.SUCCESS)
                            && mediatorResponse6.getOperationStatus().equals(OperationStatus.SUCCESS)){
                        message = mediatorResponse5.getMessage();
                    }
                    else if(mediatorResponse5.getOperationStatus().equals(OperationStatus.SUCCESS)
                            && !mediatorResponse6.getOperationStatus().equals(OperationStatus.SUCCESS)){
                        message = mediatorResponse6.getMessage();
                    }
                    else{
                        message = "The payment and the order are canceled";
                    }
                }
            }else{
                message = mediatorResponse1.getMessage();
            }
        }else{
            message = Messages.getString("empty_json");
        }
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
