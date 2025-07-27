package com.isuru.accme.service;

import com.isuru.accme.domain.entity.TransactionEntity;

import java.util.List;

public interface TransactionService {

    List<TransactionEntity> getTransactions(String userId);

    TransactionEntity createTransaction(TransactionEntity transactionEntity);

    TransactionEntity getTransaction(String entryId);
}
