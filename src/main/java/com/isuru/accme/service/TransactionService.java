package com.isuru.accme.service;

import com.isuru.accme.domain.entity.TransactionEntity;

import java.util.List;

public interface TransactionService {

    List<TransactionEntity> getEntries(String userId);

    TransactionEntity createEntry(TransactionEntity transactionEntity);

    TransactionEntity getEntry(String entryId);
}
