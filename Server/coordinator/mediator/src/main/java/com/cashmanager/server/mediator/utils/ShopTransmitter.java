package com.cashmanager.server.mediator.utils;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.OrderDto;
import com.cashmanager.server.common.enumeration.OperationStatus;
import com.cashmanager.server.common.enumeration.OperationStep;
import com.cashmanager.server.common.enumeration.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Component
public class ShopTransmitter {
    private final WebClient webClient;

    public ShopTransmitter(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Function that send the HttpRequest to the OrderController to create a new order
     * @param cart list of products and quantity
     * @return TransmitterResponse
     */
    public TransmitterResponse<OrderDto> createOrder(CartDto cart){
        TransmitterResponse<OrderDto> response = new TransmitterResponse<>();
        response.setOperationStep(OperationStep.CREATE_ORDER);

        //Communication with the ShopModule
        try{
            Mono<ResponseEntity<OrderDto>> monoResponse = webClient.post()
                    .uri("/shop/orders")
                    .body(Mono.just(cart), CartDto.class)
                    .retrieve()
                    .toEntity(OrderDto.class);
            ResponseEntity<OrderDto> responseEntity = monoResponse.block(Duration.of(1500, ChronoUnit.MILLIS));
            assert responseEntity != null;
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                response.setData(responseEntity.getBody());
                response.setOperationStatus(OperationStatus.SUCCESS);
            }else{
                response.setMessage(Objects.requireNonNull(responseEntity.getBody()).toString());
                response.setOperationStatus(OperationStatus.ERROR);
            }
        }catch (Exception e){
            response.setOperationStatus(OperationStatus.ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public TransmitterResponse<String> updateOrder(UUID orderId, OrderStatus orderStatus){
        TransmitterResponse<String> response = new TransmitterResponse<>();
        response.setOperationStep(OperationStep.UPDATE_ORDER);

        //Communication with the ShopModule
        try {
            Mono<ResponseEntity<String>> monoResponse = webClient.post()
                    .uri("/shop/orders/" + orderId)
                    .body(Mono.just(orderStatus), OrderStatus.class)
                    .retrieve()
                    .toEntity(String.class);

            ResponseEntity<String> responseEntity = monoResponse.block(Duration.of(1000, ChronoUnit.MILLIS));
            assert responseEntity != null;
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                response.setOperationStatus(OperationStatus.SUCCESS);
            }else{
                response.setOperationStatus(OperationStatus.ERROR);
                response.setMessage(responseEntity.getBody());
            }
        }catch (Exception e){
            response.setOperationStatus(OperationStatus.ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public TransmitterResponse<String> reverseInventory(UUID orderId){
        //TODO communication with the ShopModule

        return null;
    }
}
