package com.cashmanager.server.database.repository;

import com.cashmanager.server.database.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {

    Optional<PaymentMethod> findByCreditCardNumberAndCvc(String creditCardNumber, String cvc);

    Optional<PaymentMethod> findByCheckNumber(int checkNumber);
}