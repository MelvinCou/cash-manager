package com.cashmanager.server.account.service;

import com.cashmanager.server.account.verification.CheckVerification;
import com.cashmanager.server.account.verification.CreditCardVerification;
import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.entity.PaymentMethod;
import com.cashmanager.server.database.entity.TransactionLog;
import com.cashmanager.server.database.enumeration.LogSeverity;
import com.cashmanager.server.database.mapper.TransactionMapper;
import com.cashmanager.server.database.repository.PaymentMethodRepository;
import com.cashmanager.server.database.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class implementing IPaymentMethodService interface,\n
 * Used to verify and validate payment method during payment step.
 */
@Service
public class PaymentMethodService implements IPaymentMethodService{

    private final PaymentMethodRepository paymentMethodRepository;
    private final TransactionLogRepository transactionLogRepository;
    private TransactionMapper transactionMapper;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, TransactionLogRepository transactionLogRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.transactionLogRepository = transactionLogRepository;
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
            if (paymentMethod.isEmpty()) { // Case of paymentMethod credit card isn't found
                transactionLogRepository.save(new TransactionLog(null, transactionMapper.transactionDtoToTransaction(transactionDto), LocalDateTime.now(), LogSeverity.ERROR, "none credit card payment method was found"));
                transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
                return paymentMethod;
            }
            transactionLogRepository.save(new TransactionLog(null, transactionMapper.transactionDtoToTransaction(transactionDto), LocalDateTime.now(), LogSeverity.INFO, "credit card payment method was found"));
            transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);

        } else if (CheckVerification.isACheck(paymentMethodDto)) {

            paymentMethod = paymentMethodRepository.findByCheckNumber(paymentMethodDto.getCheckNumber());
            if (paymentMethod.isEmpty()) { // Case of paymentMethod check wasn't found
                transactionLogRepository.save(new TransactionLog(null, transactionMapper.transactionDtoToTransaction(transactionDto), LocalDateTime.now(), LogSeverity.ERROR, "none check payment method was found"));
                transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
                return paymentMethod;
            }
            transactionLogRepository.save(new TransactionLog(null, transactionMapper.transactionDtoToTransaction(transactionDto), LocalDateTime.now(), LogSeverity.INFO, "check payment method was found"));
            transactionDto.setTransactionStatus(TransactionStatus.PAYMENT_IN_PROGRESS);

        } else { // Case of PaymentMethod wasn't check or card
            transactionLogRepository.save(new TransactionLog(null, transactionMapper.transactionDtoToTransaction(transactionDto), LocalDateTime.now(), LogSeverity.ERROR, "none payment method was found"));
            transactionDto.setTransactionStatus(TransactionStatus.INCORRECT_PAYMENT_INFO);
        }
        return paymentMethod;
    }
}
