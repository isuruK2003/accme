package com.isuru.accme.mapper;

import com.isuru.accme.domain.dto.request.CreateTransactionRequestDto;
import com.isuru.accme.domain.dto.response.TransactionResponseDto;
import com.isuru.accme.domain.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    TransactionResponseDto toTransactionDto(TransactionEntity transactionEntity);

    TransactionEntity toTransactionEntity(CreateTransactionRequestDto createTransactionRequestDto);
}
