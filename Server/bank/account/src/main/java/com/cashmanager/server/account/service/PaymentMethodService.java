package com.cashmanager.server.account.service;

import com.cashmanager.server.account.verification.CheckVerification;
import com.cashmanager.server.account.verification.CreditCardVerification;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.entity.PaymentMethod;
import com.cashmanager.server.database.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService implements IPaymentMethodService{

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    /**
     * Used to get and validate if PaymentMethod is viable
     * @param paymentMethodDto  - PaymentMethod send from mediator
     * @param transactionDto    - Current transaction
     * @return                  - User's payment method, returned from database
     */
    @Override
    public Optional<PaymentMethod> checkPaymentMethodViability(PaymentMethodDto paymentMethodDto, TransactionDto transactionDto) {
        Optional<PaymentMethod> paymentMethod = Optional.empty();
        if (CreditCardVerification.isACreditCard(paymentMethodDto)) {

            paymentMethod = paymentMethodRepository.findByCreditCardNumberAndCvc(paymentMethodDto.getCreditCardNumber(), paymentMethodDto.getCvc());
            if (paymentMethod.isEmpty()) {
                // TODO -> Create log, case of paymentMethod credit card isn't found
                transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
                return paymentMethod;
            }
            transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);
        } else if (CheckVerification.isACheck(paymentMethodDto)) {

            paymentMethod = paymentMethodRepository.findByCheckNumber(paymentMethodDto.getCheckNumber());
            if (paymentMethod.isEmpty()) {
                // TODO -> Create log, case of paymentMethod check isn't found
                transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
                return paymentMethod;
            }
            transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);
        } else {
            // TODO -> Create log, case of PaymentMethod isn't check or card
            transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
        }
        return paymentMethod;
    }
}
