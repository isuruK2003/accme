package com.isuru.accme.controller;

import com.isuru.accme.domain.dto.CreateTransactionRequest;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/entries/{userId}")
public class TransactionController {

    private final TransactionService transactionService;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getEntries(@PathVariable String userId) {
        if (!userService.isExists(userId)) {
            throw new UserNotFoundException(String.format("User with id=%s not found", userId));
        }

        List<TransactionEntity> entryEntities = transactionService.getEntries(userId);

        List<TransactionDto> transactionDtoList = entryEntities.stream()
                .map(e -> TransactionDto.builder()
                        .id(e.getId())
                        .amount(e.getAmount())
                        .build())
                .toList();

        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createEntry(
            @PathVariable String userId,
            @RequestBody CreateTransactionRequest createTransactionRequest) {
        if (!userService.isExists(userId)) {
            throw new UserNotFoundException(String.format("User with id=%s not found", userId));
        }
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .amount(createTransactionRequest.getAmount())
                .userId(userId)
                .build();
        TransactionEntity createdEntry = transactionService.createEntry(transactionEntity);
        TransactionDto transactionDto = TransactionDto.builder()
                .id(createdEntry.getId())
                .amount(createdEntry.getAmount())
                .build();
        return new ResponseEntity<>(transactionDto, HttpStatus.CREATED);
    }
}
