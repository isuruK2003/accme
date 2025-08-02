package com.isuru.accme.service.impl;

import com.isuru.accme.domain.entity.TransactionEntity;
import com.isuru.accme.exception.AccountNotFoundException;
import com.isuru.accme.exception.TransactionNotFoundException;
import com.isuru.accme.exception.UserNotFoundException;
import com.isuru.accme.repository.TransactionRepository;
import com.isuru.accme.service.AccountService;
import com.isuru.accme.service.TransactionService;
import com.isuru.accme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserService userService;

    private final AccountService accountService;

    @Override
    public List<TransactionEntity> getTransactions(String userId) throws UserNotFoundException {
        if (!userService.isExists(userId)) {
            throw new UserNotFoundException(userId);
        }
        return transactionRepository.findAllByUserId(userId).orElse(Collections.emptyList());
    }

    @Override
    public TransactionEntity createTransaction(TransactionEntity transactionEntity) throws UserNotFoundException, AccountNotFoundException {
        if (!userService.isExists(transactionEntity.getUserId())) {
            throw new UserNotFoundException(transactionEntity.getUserId());
        }
        if (!accountService.isExists(transactionEntity.getAccountId())) {
            throw new AccountNotFoundException(transactionEntity.getAccountId());
        }
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public TransactionEntity getTransaction(String transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(transactionId));
    }
}
