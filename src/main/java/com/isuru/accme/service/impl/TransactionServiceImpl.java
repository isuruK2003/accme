package com.isuru.accme.service.impl;

import com.isuru.accme.domain.entity.TransactionEntity;
import com.isuru.accme.exception.TransactionNotFoundException;
import com.isuru.accme.repository.TransactionRepository;
import com.isuru.accme.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionEntity> getEntries(String userId) {
        return transactionRepository.findAllByUserId(userId).orElse(Collections.emptyList());
    }

    @Override
    public TransactionEntity createEntry(TransactionEntity transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public TransactionEntity getEntry(String entryId) {
        return transactionRepository.findById(entryId)
                .orElseThrow(() -> new TransactionNotFoundException(
                        String.format("Entry with entryId=%s cannot be found", entryId)
                ));
    }
}
