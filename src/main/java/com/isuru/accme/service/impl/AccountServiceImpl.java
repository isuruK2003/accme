package com.isuru.accme.service.impl;

import com.isuru.accme.domain.entity.AccountEntity;
import com.isuru.accme.exception.UserNotFoundException;
import com.isuru.accme.repository.AccountRepository;
import com.isuru.accme.service.AccountService;
import com.isuru.accme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserService userService;

    public Boolean isExists(String accountId) {
        return accountRepository.existsById(accountId);
    }

    public AccountEntity createAccount(AccountEntity accountEntity) throws UserNotFoundException {
        if (!userService.isExists(accountEntity.getUserId())) {
            throw new UserNotFoundException(accountEntity.getUserId());
        }
        return accountRepository.save(accountEntity);
    }

    @Override
    public List<AccountEntity> getAccounts(String userId) throws UserNotFoundException {
        if (!userService.isExists(userId)) {
            throw new UserNotFoundException(userId);
        }
        return accountRepository.findAllByUserId(userId);
    }
}
