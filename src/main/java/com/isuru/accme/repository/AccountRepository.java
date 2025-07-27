package com.isuru.accme.repository;

import com.isuru.accme.domain.entity.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<AccountEntity, String> {
    List<AccountEntity> findAllByUserId(String userId);
}
