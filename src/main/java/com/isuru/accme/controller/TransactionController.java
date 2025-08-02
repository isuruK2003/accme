package com.isuru.accme.controller;

import com.isuru.accme.domain.dto.request.CreateTransactionRequestDto;
import com.isuru.accme.domain.dto.response.TransactionResponseDto;
import com.isuru.accme.domain.entity.TransactionEntity;
import com.isuru.accme.mapper.TransactionMapper;
import com.isuru.accme.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody CreateTransactionRequestDto createTransactionRequestDto) {
        TransactionEntity transactionEntity = transactionMapper.toTransactionEntity(createTransactionRequestDto);
        TransactionEntity createdTransaction = transactionService.createTransaction(transactionEntity);
        TransactionResponseDto transactionResponseDto = transactionMapper.toTransactionDto(createdTransaction);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(@PathVariable String userId) {
        List<TransactionEntity> transactionEntities = transactionService.getTransactions(userId);
        List<TransactionResponseDto> transactionResponseDtoList = transactionEntities.stream()
                .map(transactionMapper::toTransactionDto)
                .toList();
        return new ResponseEntity<>(transactionResponseDtoList, HttpStatus.OK);
    }
}
