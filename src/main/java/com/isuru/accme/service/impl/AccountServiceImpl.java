package com.isuru.accme.service.impl;

import com.isuru.accme.domain.entity.AccountEntity;
import com.isuru.accme.repository.AccountRepository;
import com.isuru.accme.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountEntity createAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }

    @Override
    public List<AccountEntity> getAccounts(String userId) {
        return accountRepository.findAllByUserId(userId);
    }
}
