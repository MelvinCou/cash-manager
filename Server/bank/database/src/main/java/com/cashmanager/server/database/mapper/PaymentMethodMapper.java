package com.cashmanager.server.database.mapper;

import com.cashmanager.server.common.dto.PaymentMethodDto;
import com.cashmanager.server.database.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface used to convert PaymentMethodDto to PaymentMethod entity
 */
@Mapper
public interface PaymentMethodMapper {
    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    PaymentMethodDto paymentMethodToPaymentMethodDto(PaymentMethod paymentMethod);

    PaymentMethod paymentMethodDtoToPaymentMethod(PaymentMethodDto paymentMethodDto);
}
