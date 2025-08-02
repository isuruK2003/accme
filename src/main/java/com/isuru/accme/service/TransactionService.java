package com.isuru.accme.service;

import com.isuru.accme.domain.entity.TransactionEntity;
import com.isuru.accme.exception.UserNotFoundException;

import java.util.List;

public interface TransactionService {

    List<TransactionEntity> getTransactions(String userId) throws UserNotFoundException;

    TransactionEntity createTransaction(TransactionEntity transactionEntity) throws UserNotFoundException;

    TransactionEntity getTransaction(String entryId);
}
