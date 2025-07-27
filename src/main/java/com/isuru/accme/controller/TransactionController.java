package com.isuru.accme.controller;

import com.isuru.accme.domain.dto.request.CreateTransactionRequest;
import com.isuru.accme.domain.dto.TransactionDto;
import com.isuru.accme.domain.entity.TransactionEntity;
import com.isuru.accme.exception.UserNotFoundException;
import com.isuru.accme.service.TransactionService;
import com.isuru.accme.service.UserService;
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

    private final UserService userService;

    @GetMapping("{userId}")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable String userId) {
        if (!userService.isExists(userId)) {
            throw new UserNotFoundException(userId);
        }

        List<TransactionEntity> transactionEntities = transactionService.getTransactions(userId);

        List<TransactionDto> transactionDtoList = transactionEntities.stream()
                .map(e -> TransactionDto.builder()
                        .id(e.getId())
                        .userId(e.getUserId())
                        .accountId(e.getAccountId())
                        .amount(e.getAmount())
                        .build())
                .toList();

        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(
            @RequestBody CreateTransactionRequest createTransactionRequest) {
        if (!userService.isExists(createTransactionRequest.getUserId())) {
            throw new UserNotFoundException(createTransactionRequest.getUserId());
        }
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .userId(createTransactionRequest.getUserId())
                .accountId(createTransactionRequest.getAccountId())
                .amount(createTransactionRequest.getAmount())
                .build();
        TransactionEntity createdTransaction = transactionService.createTransaction(transactionEntity);
        TransactionDto transactionDto = TransactionDto.builder()
                .id(createdTransaction.getId())
                .userId(createdTransaction.getUserId())
                .accountId(createdTransaction.getAccountId())
                .amount(createdTransaction.getAmount())
                .build();
        return new ResponseEntity<>(transactionDto, HttpStatus.CREATED);
    }
}
