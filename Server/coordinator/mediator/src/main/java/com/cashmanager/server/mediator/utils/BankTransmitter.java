package com.cashmanager.server.mediator.utils;

import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.OperationStatus;
import com.cashmanager.server.common.enumeration.OperationStep;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class BankTransmitter {
    private final WebClient webClient;

    public BankTransmitter(WebClient webClient) {
        this.webClient = webClient;
    }

    public TransmitterResponse<TransactionDto> createTransaction(PaymentMethodDto paymentMethod, BigDecimal amount, String receiver){
        //TODO à supprimer
        TransactionDto transaction = new TransactionDto(LocalDateTime.now().toString(),TransactionStatus.PAYMENT_IN_PROGRESS,paymentMethod,amount, receiver);
        TransmitterResponse<TransactionDto> response = new TransmitterResponse<>();
        response.setOperationStep(OperationStep.PAYMENT_DONE);
        response.setOperationStatus(OperationStatus.SUCCESS);
        response.setData(transaction);

        //TODO communication with BankController
        try{
            Mono<ResponseEntity<String>> monoTransac = webClient.post()
                    .uri("/webclient")
                    .body(Mono.just("paymentMethod"), String.class)
                    .retrieve()
                    .toEntity(String.class);

            monoTransac.subscribe(
                    responseEntity -> {
                        System.out.println("Status: " + responseEntity.getStatusCode());
                        System.out.println("Location URI: " + responseEntity.getHeaders().getLocation());
                        System.out.println("Message: " + responseEntity.getBody());
                    });
        }catch (Exception e){
            System.out.println("error");
        }


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
