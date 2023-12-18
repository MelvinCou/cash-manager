package com.cashmanager.server.mediator.utils;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.OrderDto;
import com.cashmanager.server.common.enumeration.OperationStatus;
import com.cashmanager.server.common.enumeration.OperationStep;
import com.cashmanager.server.common.enumeration.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ShopTransmitter {
    public TransmitterResponse<OrderDto> createOrder(CartDto cart){
        //TODO à supprimer
        OrderDto order = new OrderDto(LocalDateTime.now().toString(),cart);
        order.setId(UUID.fromString("df6c25cc-b0f6-45e1-be8a-d6de6bf7e463"));
        TransmitterResponse<OrderDto> response = new TransmitterResponse<>();
        response.setData(order);
        response.setOperationStep(OperationStep.CREATE_ORDER);
        response.setOperationStatus(OperationStatus.SUCCESS);

        //TODO communication with the ShopController
        return response;
    }

    public TransmitterResponse<String> updateOrder(OrderStatus orderStatus, UUID orderId){
        //TODO à supprimer
        TransmitterResponse<String> response = new TransmitterResponse<>();
        response.setOperationStep(OperationStep.UPDATE_ORDER);
        response.setOperationStatus(OperationStatus.SUCCESS);


        //TODO communication with the ShopController
        return response;
    }

    public TransmitterResponse<String> reverseInventory(UUID orderId){
        //TODO communication with the ShopController


        return null;
    }
}
