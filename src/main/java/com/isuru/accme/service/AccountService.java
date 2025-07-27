package com.isuru.accme.service;

import com.isuru.accme.domain.entity.AccountEntity;

import java.util.List;

public interface AccountService {

    AccountEntity createAccount(AccountEntity accountEntity);

    List<AccountEntity> getAccounts(String userId);
}
