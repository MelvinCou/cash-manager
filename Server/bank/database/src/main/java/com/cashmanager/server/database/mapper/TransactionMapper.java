package com.cashmanager.server.database.mapper;

import com.cashmanager.server.common.dto.TransactionDto;
import com.cashmanager.server.database.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface used to convert TransactionDto to Transaction entity
 */
@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDto transactionToTransactionDto(Transaction transaction);

    @Mapping(target = "date", source = "date", dateFormat = "MM/yy", ignore = true)
    Transaction transactionDtoToTransaction(TransactionDto transactionDto);
}
