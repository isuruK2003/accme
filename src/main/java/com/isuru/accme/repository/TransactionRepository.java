package com.isuru.accme.repository;

import com.isuru.accme.domain.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

    Optional<List<TransactionEntity>> findAllByUserId(String userId);
}
