package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
    Optional<PaymentMethod> findByCreditCardNumberAndCvc(String creditCardNumber, String cvc);
}