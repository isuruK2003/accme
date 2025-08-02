package com.isuru.accme.service;

import com.isuru.accme.domain.entity.AccountEntity;
import com.isuru.accme.exception.UserNotFoundException;

import java.util.List;

public interface AccountService {

    AccountEntity createAccount(AccountEntity accountEntity) throws UserNotFoundException;

    List<AccountEntity> getAccounts(String userId) throws UserNotFoundException;
}
