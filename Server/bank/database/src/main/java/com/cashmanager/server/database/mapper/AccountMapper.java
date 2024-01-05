package com.cashmanager.server.database.mapper;

import com.cashmanager.server.common.dto.AccountDto;
import com.cashmanager.server.database.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

/**
 * Mapper interface used to convert AccountDto to Account entity
 */
@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto accountToAccountDto(Optional<Account> account);

    Account accountDtoToAccount(Optional<AccountDto> accountDto);
}
