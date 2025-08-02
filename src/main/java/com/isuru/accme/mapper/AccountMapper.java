package com.isuru.accme.mapper;

import com.isuru.accme.domain.dto.request.CreateAccountRequestDto;
import com.isuru.accme.domain.dto.response.AccountResponseDto;
import com.isuru.accme.domain.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountResponseDto toAccountDto(AccountEntity accountEntity);

    AccountEntity toAccountEntity(CreateAccountRequestDto accountRequestDto);
}
